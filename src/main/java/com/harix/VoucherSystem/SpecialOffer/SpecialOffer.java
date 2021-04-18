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

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.harix.VoucherSystem.Voucher.Voucher;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="specialOfferId")
public class SpecialOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer specialOfferId;
	private String specialOfferName;
	private double specialOfferDiscountPercentage;
	private Date specialOfferExpiryDate;
	@JsonManagedReference(value = "specialoffer")
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Voucher.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "voucher_code", referencedColumnName = "voucherCode")
	private Voucher voucher;
	public Integer getSpecialOfferId() {
		return specialOfferId;
	}
	public void setSpecialOfferId(Integer specialOfferId) {
		this.specialOfferId = specialOfferId;
	}
	public String getSpecialOfferName() {
		return specialOfferName;
	}
	public void setSpecialOfferName(String specialOfferName) {
		this.specialOfferName = specialOfferName;
	}
	public double getSpecialOfferDiscountPercentage() {
		return specialOfferDiscountPercentage;
	}
	public void setSpecialOfferDiscountPercentage(double specialOfferDiscountPercentage) {
		this.specialOfferDiscountPercentage = specialOfferDiscountPercentage;
	}
	public Date getSpecialOfferExpiryDate() {
		return specialOfferExpiryDate;
	}
	public void setSpecialOfferExpiryDate(Date specialOfferExpiryDate) {
		this.specialOfferExpiryDate = specialOfferExpiryDate;
	}
	public Voucher getVoucher() {
		return voucher;
	}
	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(specialOfferDiscountPercentage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specialOfferExpiryDate == null) ? 0 : specialOfferExpiryDate.hashCode());
		result = prime * result + ((specialOfferId == null) ? 0 : specialOfferId.hashCode());
		result = prime * result + ((specialOfferName == null) ? 0 : specialOfferName.hashCode());
		result = prime * result + ((voucher == null) ? 0 : voucher.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecialOffer other = (SpecialOffer) obj;
		if (Double.doubleToLongBits(specialOfferDiscountPercentage) != Double
				.doubleToLongBits(other.specialOfferDiscountPercentage))
			return false;
		if (specialOfferExpiryDate == null) {
			if (other.specialOfferExpiryDate != null)
				return false;
		} else if (!specialOfferExpiryDate.equals(other.specialOfferExpiryDate))
			return false;
		if (specialOfferId == null) {
			if (other.specialOfferId != null)
				return false;
		} else if (!specialOfferId.equals(other.specialOfferId))
			return false;
		if (specialOfferName == null) {
			if (other.specialOfferName != null)
				return false;
		} else if (!specialOfferName.equals(other.specialOfferName))
			return false;
		if (voucher == null) {
			if (other.voucher != null)
				return false;
		} else if (!voucher.equals(other.voucher))
			return false;
		return true;
	}

	
	
}
