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
package com.vasslatam.sakila.endpoint;

import com.vasslatam.sakila.domain.Actor;
import com.vasslatam.sakila.service.ActorService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
@Path("actor")
@Produces(APPLICATION_JSON)
@Component
public class ActorEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorEndpoint.class);
    @Autowired
    private ActorService actorService;

    @PostConstruct
    public void init() {
        LOGGER.info("Iniciando ActorEndpoint [OK]");
    }

    @GET
    public Response findAll() {
        List<Actor> actors = actorService.findAll();
        return Response.ok(actors).build();
    }

}
