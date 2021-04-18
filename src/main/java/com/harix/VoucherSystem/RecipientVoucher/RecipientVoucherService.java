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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.harix.VoucherSystem.Recipient.Recipient;
import com.harix.VoucherSystem.Recipient.RecipientService;
import com.harix.VoucherSystem.SpecialOffer.SpecialOfferService;
import com.harix.VoucherSystem.Voucher.Voucher;
import com.harix.VoucherSystem.Voucher.VoucherService;

@Service
@EnableScheduling
public class RecipientVoucherService {
	
	@Autowired
	private SpecialOfferService spOfferService;
	@Autowired
	private RecipientVoucherRepo recipientVoucherRepo;
	@Autowired
	private VoucherService voucherService;
	@Autowired
	private RecipientService recipientService;
	
	public String validateVoucher(String email, int voucherCode) {
		double percentage = 0;
		String valid = "The voucher is Not Valid";
		RecipientVoucherKey recipientVoucherKey = new RecipientVoucherKey(email,voucherCode);
		Optional<RecipientVoucher> recipientVoucher = recipientVoucherRepo.findById(recipientVoucherKey);
		if(recipientVoucher.isPresent() //check if recipientEmail and voucherCode exist 
				&& !recipientVoucher.get().isVoucherUsedFlag()){ //and if already used
			updateVoucherFlag(recipientVoucher.get());	//update voucher used date	
			percentage = spOfferService.getOfferPercentage(recipientVoucher.get().getVoucher());//get percentage from special_offer table
			valid = "The voucher is Valid. The discount is "+percentage+"%";
		}	
		
		return valid;
	}

	public List<RecipientVoucher> getAllRecipientVoucher() {
		return recipientVoucherRepo.findAll();
	}

	public void insertRecipientVoucher(RecipientVoucher newRecipientVouchers) {
		recipientVoucherRepo.save(newRecipientVouchers);	
	}
	
	public String getRecipientVoucher(String email) {
		List<RecipientVoucher> recipientVouchers = recipientVoucherRepo.findByIdRecipientEmail(email);
		StringBuilder result = new StringBuilder("The Voucher Code is ");
		for(RecipientVoucher recipientVoucher: recipientVouchers) {// get all vouchers from a recipient
			result.append(System.getProperty("line.separator"));
			result.append("\t"+recipientVoucher.getVoucher().getVoucherCode());
			result.append("\t");
			result.append("\""+spOfferService.getOfferName(recipientVoucher.getVoucher())+"\" ");
			result.append(System.getProperty("line.separator"));
		}
		return result.toString();
	}
	

	public void updateVoucherFlag(RecipientVoucher recipientVoucher) {
		Date date = new Date();
		recipientVoucher.setVoucherUsedFlag(true);
		recipientVoucher.setVoucherUsageDate(date);
		recipientVoucherRepo.save(recipientVoucher);
		
	}
	
	@Scheduled(fixedRate = 60000)//will run each one minute
	public void autoInsertRecipientVoucher() {
		Optional<RecipientVoucher> opRecipientVoucher = null;
		RecipientVoucher recipientVoucher = null;
		RecipientVoucherKey recipientVoucherKey = null;
		List<Voucher> vouchers = voucherService.getAllVoucher();
		List<Recipient> recipients = recipientService.getAllRecipient();
		for(Voucher voucher: vouchers) {
			recipientVoucherKey = new RecipientVoucherKey();
			recipientVoucherKey.setVoucherCode(voucher.getVoucherCode());
			for(Recipient recipient: recipients) {
				recipientVoucherKey.setRecipientEmail(recipient.getRecipientEmail());
				opRecipientVoucher = recipientVoucherRepo.findById(recipientVoucherKey);
				if(!opRecipientVoucher.isPresent()) {//check each voucher with each recipient, if not exist will insert
					recipientVoucher = new RecipientVoucher();
					recipientVoucherKey.setRecipientEmail(recipient.getRecipientEmail());
					recipientVoucher.setId(recipientVoucherKey);
					recipientVoucher.setVoucher(voucher);
					recipientVoucher.setRecipient(recipient);
					insertRecipientVoucher(recipientVoucher);
				}
			}
		}
		
	}
	
}
