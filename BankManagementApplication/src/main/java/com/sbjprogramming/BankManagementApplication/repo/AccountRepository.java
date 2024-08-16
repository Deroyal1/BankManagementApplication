package com.sbjprogramming.BankManagementApplication.repo;

import org.springframework.data.jpa.repository.*;
import com.sbjprogramming.BankManagementApplication.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
