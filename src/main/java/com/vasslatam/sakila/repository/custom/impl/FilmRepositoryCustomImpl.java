/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.vasslatam.sakila.repository.custom.impl;

import com.vasslatam.sakila.domain.Actor_;
import com.vasslatam.sakila.domain.Film;
import com.vasslatam.sakila.domain.FilmActor;
import com.vasslatam.sakila.domain.FilmActor_;
import com.vasslatam.sakila.domain.Inventory;
import com.vasslatam.sakila.domain.Inventory_;
import com.vasslatam.sakila.domain.Store_;
import com.vasslatam.sakila.repository.custom.FilmRepositoryCustom;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
public class FilmRepositoryCustomImpl implements FilmRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Film> findByActorName(String actorName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Film> cq = cb.createQuery(Film.class);
        Root<FilmActor> filmActor = cq.from(FilmActor.class);
        String nameWildcard = '%' + actorName.replace(' ', '%') + '%';
        cq.select(filmActor.get(FilmActor_.film))
                .where(
                        cb.or(
                                cb.like(filmActor.get(FilmActor_.actor).get(Actor_.firstName), nameWildcard),
                                cb.like(filmActor.get(FilmActor_.actor).get(Actor_.lastName), nameWildcard)
                        )
                );
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Film> findByStoreId(int storeId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Film> cq = cb.createQuery(Film.class);
        Root<Inventory> inventory = cq.from(Inventory.class);
        cq.select(inventory.get(Inventory_.film))
                .distinct(true)
                .where(
                        cb.equal(inventory.get(Inventory_.store).get(Store_.storeId), storeId)
                );
        TypedQuery<Film> query = em.createQuery(cq);
        return query.getResultList();
    }

}
