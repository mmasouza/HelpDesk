package com.home.helpdesk.api.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.home.helpdesk.api.entity.Ticket;

public interface TicketRepository  extends MongoRepository<Ticket, String>{
	
Page<Ticket> findByUserIdOrderByDateDesc(Pageable pages, String userId);
	
	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc (
			String title, String Status, String priority, Pageable pages);
	
	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc (
			String title, String Status, String priority, Pageable pages);
	
	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssigmerUserIdOrderByDateDesc (
			String title, String Status, String priority, Pageable pages);
	
	Page<Ticket> findByNumber (Integer number, Pageable pageable);

}
