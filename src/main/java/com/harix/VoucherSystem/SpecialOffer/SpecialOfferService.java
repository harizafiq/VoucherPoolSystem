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
package com.harix.VoucherSystem.SpecialOffer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harix.VoucherSystem.Voucher.Voucher;
import com.harix.VoucherSystem.Voucher.VoucherService;


@Service
public class SpecialOfferService {

	@Autowired
	private SpecialOfferRepo spOfferRepo;
	@Autowired
	private VoucherService voucherService;
	
	public Optional<SpecialOffer> createNewSpecialOffer(SpecialOffer newSpOffer) {
		Voucher voucher = new Voucher();
		voucher.setVoucherCode(voucherService.generatedVoucherCode());
		voucher.setVoucherExpiryDate(newSpOffer.getSpecialOfferExpiryDate());
		newSpOffer.setVoucher(voucher);
		spOfferRepo.save(newSpOffer);
		voucherService.generateVoucherForRecipient(voucher);
			
		return spOfferRepo.findById(newSpOffer.getSpecialOfferId());
	}
	
	public List<SpecialOffer> listSpecialOffer(){
		return spOfferRepo.findAll();
	}
	
	public void deleteSpecialOffer(int spOfferId) {
		spOfferRepo.deleteById(spOfferId);
	}

	public double getOfferPercentage(Voucher voucher) {
		SpecialOffer spOffer = new SpecialOffer();
		spOffer = spOfferRepo.findByVoucherVoucherCode(voucher.getVoucherCode());
		return spOffer.getSpecialOfferDiscountPercentage();
	}
	
	public String getOfferName(Voucher voucher) {
		SpecialOffer spOffer = new SpecialOffer();
		spOffer = spOfferRepo.findByVoucherVoucherCode(voucher.getVoucherCode());
		return spOffer.getSpecialOfferName();
	}
}
