package com.afshin.person.domain.service;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 10 - 31
 * @Time 11:06 PM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
import com.afshin.person.domain.entity.Contact;
import com.afshin.person.domain.entity.Customer;
import com.afshin.person.infrastructure.repository.ContactDao;
import com.afshin.person.infrastructure.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service//Logic
public class PersonSrv {
    @Autowired private CustomerDao custDao;
    @Autowired private ContactDao conDao;

    public List<Customer> find(Integer code,Integer pageNum) throws Exception {
        List<Customer> returnData;
        Pageable somedata =  PageRequest.of(pageNum, 10, Sort.by("customerpk").descending());
        if(code==0) returnData = (custDao.findAll(somedata)).getContent();
        else returnData=custDao.findByCustomerpk(code);
        return returnData;
    }
    @Transactional
    public String delete(Integer code) throws Exception {
            conDao.deleteBycustomerfk(code);
            custDao.deleteById(code);
            return "{'code':1,'message':'record code "+code+" is deleted'}";
    }

    public String save(Customer viewCustomer) throws Exception {
        if(viewCustomer.getCustomerpk()==null) {//not pk => maybe New customer
            if(custDao.findByNationalkey(viewCustomer.getNationalkey()).size()==0) //No NationalKey => Add New Customer
                return "{'code':1,'message':'record code "+insertPerson(viewCustomer)+" is added'}";
            else
                return "{'code':0,'message':'National code "+viewCustomer.getNationalkey()+" is duplicate'}";
        }else {//Exist NationalKey  => Edit Current Customer
            List<Customer> dbCustomers = custDao.findByCustomerpk(viewCustomer.getCustomerpk());
            if(dbCustomers.size()==0) return "{'code':0,'message':'record code "+viewCustomer.getCustomerpk()+" is Not found'}";
            else return "{'code':2,'message':'record code "+updatePerson(viewCustomer,dbCustomers.get(0))+" is updated'}";
        }
    }
    @Transactional
    public Integer insertPerson(Customer viewCustomer){
        viewCustomer = custDao.save(viewCustomer);
        if(viewCustomer.getContactsByCustomerpk()!=null)
            if(viewCustomer.getContactsByCustomerpk().size()>0){
                Integer customerCode = viewCustomer.getCustomerpk();
                viewCustomer.getContactsByCustomerpk().forEach(contact -> contact.setCustomerfk(customerCode));
                conDao.saveAll(viewCustomer.getContactsByCustomerpk());
            }
        return viewCustomer.getCustomerpk();
    }
    @Transactional
    public Integer updatePerson(Customer viewCustomer,Customer dbCustomer){
        //update Customer
        dbCustomer.setCustomertypeid(viewCustomer.getCustomertypeid());
        dbCustomer.setTypedetailid(viewCustomer.getTypedetailid());
        dbCustomer.setNationalkey(viewCustomer.getNationalkey());
        dbCustomer.setBooknumber(viewCustomer.getBooknumber());
        dbCustomer.setBookserial(viewCustomer.getBookserial());
        dbCustomer.setBookserie(viewCustomer.getBookserie());
        dbCustomer.setPassportno(viewCustomer.getPassportno());
        dbCustomer.setLastname(viewCustomer.getLastname());
        dbCustomer.setFirstname(viewCustomer.getFirstname());
        dbCustomer.setCountryid(viewCustomer.getCountryid());
        dbCustomer.setCityid(viewCustomer.getCityid());
        dbCustomer.setBirthdate(viewCustomer.getBirthdate());
        dbCustomer=custDao.save(dbCustomer);
        //update Contacts
        if(viewCustomer.getContactsByCustomerpk().size()>0) {
            custDao.deleteById(dbCustomer.getCustomerpk());
            Integer customerCode = viewCustomer.getCustomerpk();
            viewCustomer.getContactsByCustomerpk().forEach(contact -> contact.setCustomerfk(customerCode));
            conDao.saveAll(viewCustomer.getContactsByCustomerpk());
        }
        return dbCustomer.getCustomerpk();
    }
}