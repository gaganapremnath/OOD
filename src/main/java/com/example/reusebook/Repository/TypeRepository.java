package com.example.reusebook.Repository;

import com.example.reusebook.Model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeRepository extends JpaRepository<TransactionType, Long> {

}
