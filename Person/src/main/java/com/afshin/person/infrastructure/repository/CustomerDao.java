package com.afshin.person.infrastructure.repository;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date Apr 26, 2021 2:58:19 AM
 * @version
 * Created by Eclipse 2020-09
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: 
*/
import java.util.List;
import com.afshin.person.domain.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Integer>{
	Page<Customer> findAll(Pageable pageable);
	List<Customer> findByNationalkey(String nationalKey);
	List<Customer> findByCustomerpk(Integer customercode);
}
