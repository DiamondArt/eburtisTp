package com.eburtis.tp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*******************************************************************
 * Person Repository
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/
@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
