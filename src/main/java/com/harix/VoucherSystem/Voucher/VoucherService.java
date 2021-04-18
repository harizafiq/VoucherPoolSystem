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
package com.harix.VoucherSystem.Voucher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harix.VoucherSystem.Recipient.Recipient;
import com.harix.VoucherSystem.Recipient.RecipientService;
import com.harix.VoucherSystem.RecipientVoucher.RecipientVoucher;
import com.harix.VoucherSystem.RecipientVoucher.RecipientVoucherKey;
import com.harix.VoucherSystem.RecipientVoucher.RecipientVoucherService;

@Service
public class VoucherService {

	@Autowired
	private VoucherRepo voucherRepo;
	@Autowired
	private RecipientService recipientService;
	@Autowired
	private RecipientVoucherService recipientVoucherService;
	
	
	public int generatedVoucherCode() {//generated random number
		Random random = new Random();
		int voucherCode = random.nextInt(99999999);
		Optional<Voucher> existingVoucher = voucherRepo.findById(voucherCode);
		if(existingVoucher.isPresent()) {//check if generated number already exist inside table
			generatedVoucherCode();
		}
		return voucherCode;
	}
	
	public Voucher generateVoucherForRecipient(Voucher voucher) {
		List<Recipient> recipients = new ArrayList<>();
		recipients = recipientService.getAllRecipient();
		Recipient updateRecipient = null;
		
		RecipientVoucher updateRerecipientVoucher = new RecipientVoucher();
		RecipientVoucherKey recipientVoucherKey = new RecipientVoucherKey();
		
		for (Recipient recipient : recipients) {//each recipient inside Recipient table will be pair with voucher
			updateRecipient = new Recipient();
			updateRecipient.setRecipientEmail(recipient.getRecipientEmail());
			updateRecipient.setRecipientName(recipient.getRecipientName());
			recipientVoucherKey.setRecipientEmail(recipient.getRecipientEmail());
			recipientVoucherKey.setVoucherCode(voucher.getVoucherCode());
			updateRerecipientVoucher.setId(recipientVoucherKey);
			updateRerecipientVoucher.setRecipient(updateRecipient);
			updateRerecipientVoucher.setVoucher(voucher);
			recipientVoucherService.insertRecipientVoucher(updateRerecipientVoucher);
		}
		
		return voucher;
	}

	public List<Voucher> getAllVoucher() {
		List<Voucher> voucher = voucherRepo.findAll();
		return voucher;
	}

	//public Optional<Voucher> createNewVoucher(Voucher voucher) {
	//	voucher.setVoucherCode(generatedVoucherCode());
	//	voucherRepo.save(voucher);
	//	generateVoucherForRecipient(voucher);
	//	return voucherRepo.findById(voucher.getVoucherCode());
	//}
}
