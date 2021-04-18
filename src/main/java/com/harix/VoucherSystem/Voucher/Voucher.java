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

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.harix.VoucherSystem.RecipientVoucher.RecipientVoucher;
import com.harix.VoucherSystem.SpecialOffer.SpecialOffer;

@Entity
@Table(name = "voucher")
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="voucherCode")
@JsonIgnoreProperties(value = "recipients")
public class Voucher {
	
	@Id
	private Integer voucherCode;
	private Date voucherExpiryDate;
	@JsonBackReference(value = "specialoffer")
	@OneToOne(targetEntity = SpecialOffer.class, mappedBy = "voucher", fetch = FetchType.LAZY)
	private SpecialOffer specialOffer;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "voucher", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RecipientVoucher> recipients;
	
	
	public Voucher() {
	}


	public Voucher(Integer voucherCode, Date voucherExpiryDate, SpecialOffer specialOffer,
			Set<RecipientVoucher> recipients) {
		super();
		this.voucherCode = voucherCode;
		this.voucherExpiryDate = voucherExpiryDate;
		this.specialOffer = specialOffer;
		this.recipients = recipients;
	}


	public Integer getVoucherCode() {
		return voucherCode;
	}


	public void setVoucherCode(Integer voucherCode) {
		this.voucherCode = voucherCode;
	}


	public Date getVoucherExpiryDate() {
		return voucherExpiryDate;
	}


	public void setVoucherExpiryDate(Date voucherExpiryDate) {
		this.voucherExpiryDate = voucherExpiryDate;
	}


	public SpecialOffer getSpecialOffer() {
		return specialOffer;
	}


	public void setSpecialOffer(SpecialOffer specialOffer) {
		this.specialOffer = specialOffer;
	}


	public Set<RecipientVoucher> getRecipients() {
		return recipients;
	}


	public void setRecipients(Set<RecipientVoucher> recipients) {
		this.recipients = recipients;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recipients == null) ? 0 : recipients.hashCode());
		result = prime * result + ((specialOffer == null) ? 0 : specialOffer.hashCode());
		result = prime * result + ((voucherCode == null) ? 0 : voucherCode.hashCode());
		result = prime * result + ((voucherExpiryDate == null) ? 0 : voucherExpiryDate.hashCode());
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
		Voucher other = (Voucher) obj;
		if (recipients == null) {
			if (other.recipients != null)
				return false;
		} else if (!recipients.equals(other.recipients))
			return false;
		if (specialOffer == null) {
			if (other.specialOffer != null)
				return false;
		} else if (!specialOffer.equals(other.specialOffer))
			return false;
		if (voucherCode == null) {
			if (other.voucherCode != null)
				return false;
		} else if (!voucherCode.equals(other.voucherCode))
			return false;
		if (voucherExpiryDate == null) {
			if (other.voucherExpiryDate != null)
				return false;
		} else if (!voucherExpiryDate.equals(other.voucherExpiryDate))
			return false;
		return true;
	}



}
