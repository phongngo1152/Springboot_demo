package com.example.demo.repository.command;

import com.example.demo.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameUserContaining(String nameUser);
    @Query("select u from User u where"
            + "(:name is null or u.nameUser like %:name%) and "
            + "(:gender is null  or u.gender = :gender) and"
            + "(:birthdate is null  or u.birthdate = :birthdate) and"
            + "(:age is null  or u.age = :age) and"
            + "(:address is null or u.address = :address)"
    )
    List<User> searchUserBy(@Param("name") String name,
                            @Param("gender") Integer gender,
                            @Param("birthdate") String birthdate,
                            @Param("address") String address,
                            @Param("age") Integer age );


    @Query("select u from User u where"
            + "(:name is null or u.nameUser like %:name%) and"
            + "(:gender is null  or u.gender = :gender) and"
            + "(:birthdate is null  or u.birthdate = :birthdate) and"
            + "(:age is null or u.age = :age) and"
            + "(:address is null or u.address = :address)")
    Page<User> searchbyAll(@Param("name") String name,
                           @Param("gender") Integer gender,
                           @Param("birthdate") String birthdate,
                           @Param("address") String address,
                           @Param("age") Integer age, Pageable pageable);

    @Query( value = "select * from User u where"
            +"(:name is null or u.nameUser like %:name%) and"
            +"(:gender is null or u.gender = :gender) and"
            +"(:birthdate is null or u.birthdate = :birthdate) and"
            +"(:age is null or u.age = :age) and"
            +"(:address is null or u.address = :address)", nativeQuery = true)
    Page<User> searchAllBySqlnative(@Param("name") String name,
                                    @Param("gender") Integer gender,
                                    @Param("birthdate") String birthdate,
                                    @Param("address") String address,
                                    @Param("age") Integer age, Pageable pageable);

}
