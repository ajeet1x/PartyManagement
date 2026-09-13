package com.task1.bookingEntity;

import java.io.Serial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class BookingCustomer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private long number;
}

@RestController
class ControllerLayer
{
	 @Autowired
	 private RepositoryLayer repo;
	 
	 @PostMapping("/add")
	 public String addCustomer(@RequestBody BookingCustomer book)
	 {
		 if(book.getName()!=null && book.getName().length()>=3)
		 {
			  if(book.getAddress()!=null && !book.getAddress().isBlank())
			  {
				   long number = book.getNumber();
				   String no1=String.valueOf(number);
				   if(no1!=null && no1.length()==10)
				   {
					   repo.save(book);
					   return "Booking Successfully Added";
				   }
				   else
				   {
					   return "Enter Valid Mobile Number";
				   }
			  }
			  else
			  {
				  return "Enter valid Address";
			  }
		 }
		 else
		 {
			 return "Enter Valid Name";
		 }
	 }
}

@Repository
interface RepositoryLayer extends JpaRepository<BookingCustomer, Integer>
{
	
}
