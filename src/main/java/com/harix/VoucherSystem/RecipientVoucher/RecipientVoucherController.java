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
package com.harix.VoucherSystem.RecipientVoucher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/rv")
public class RecipientVoucherController {

	@Autowired
	private RecipientVoucherService recipientVoucherService;
	
	@GetMapping(value = "/list")
	public List<RecipientVoucher> getAllRecipientVoucher(){
		return recipientVoucherService.getAllRecipientVoucher();
	}
	
	@GetMapping(value = "/custom")
	public ResponseEntity<String> validateVoucherCode(@RequestParam(name = "email")String email,@RequestParam(name = "code")int voucherCode){
		return new ResponseEntity<>(recipientVoucherService.validateVoucher(email, voucherCode), HttpStatus.OK);
	}
	
	@GetMapping(value = "/voucher/{id}")
	public ResponseEntity<String> getRecipientVoucher(@PathVariable(value = "id") String email){
		return new ResponseEntity<>(recipientVoucherService.getRecipientVoucher(email),HttpStatus.OK);
	}
	
	//@GetMapping(value = "/auto")
	//public void autoInsertRecipientVoucher() {
	//	recipientVoucherService.autoInsertRecipientVoucher();
	//}
}
