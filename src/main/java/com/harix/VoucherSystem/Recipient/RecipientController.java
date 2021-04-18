/*******************************************************************************
 * Copyright (C) 2021 Mohd Hariz Afiq Bin Abdul Rahman
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.harix.VoucherSystem.Recipient;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/recipient")
public class RecipientController {

	@Autowired
	private RecipientService recipientService;
	
	@GetMapping(value = "/list")
	public List<Recipient> getAllRecipient(){
		return recipientService.getAllRecipient();
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Recipient> getSpecificRecipient(@PathVariable(value = "id") String email){
		return recipientService.getSpecificRecipient(email);	
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<String> createNewRecipient(@Validated @RequestBody Recipient recipient){
		if(recipientService.saveRecipient(recipient)) {
			return new ResponseEntity<>("The user success inserted",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("The user failed inserted because already exist",HttpStatus.OK);
		}
	}
}
