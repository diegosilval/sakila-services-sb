/*
 * Copyright 2020 Diego Silva Limaco <diego.silva at apuntesdejava.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vasslatam.sakila.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author diego
 */
@Embeddable
public class FilmCategoryPK implements Serializable {

    @Column(name = "category_id")
    private int categoryId;
    @Column(name = "film_id")
    private int filmId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.categoryId;
        hash = 89 * hash + this.filmId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FilmCategoryPK other = (FilmCategoryPK) obj;
        if (this.categoryId != other.categoryId) {
            return false;
        }
        return this.filmId == other.filmId;
    }
    
    
}
