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

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.harix.VoucherSystem.Recipient.Recipient;
import com.harix.VoucherSystem.Voucher.Voucher;

@Entity
public class RecipientVoucher{
	
	@EmbeddedId
	private RecipientVoucherKey id;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId(value = "recipientEmail")
	@JoinColumn(name = "recipient_email")
	private Recipient recipient;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId(value = "voucherCode")
	@JoinColumn(name = "voucher_code")
	private Voucher voucher;
	private boolean voucherUsedFlag;
	private Date voucherUsageDate;
	
	public RecipientVoucher() {

	}

	public RecipientVoucher(RecipientVoucherKey id, Recipient recipient, Voucher voucher, boolean voucherUsedFlag,
			Date voucherUsageDate) {
		super();
		this.id = id;
		this.recipient = recipient;
		this.voucher = voucher;
		this.voucherUsedFlag = voucherUsedFlag;
		this.voucherUsageDate = voucherUsageDate;
	}

	public RecipientVoucherKey getId() {
		return id;
	}

	public void setId(RecipientVoucherKey id) {
		this.id = id;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public boolean isVoucherUsedFlag() {
		return voucherUsedFlag;
	}

	public void setVoucherUsedFlag(boolean voucherUsedFlag) {
		this.voucherUsedFlag = voucherUsedFlag;
	}

	public Date getVoucherUsageDate() {
		return voucherUsageDate;
	}

	public void setVoucherUsageDate(Date voucherUsageDate) {
		this.voucherUsageDate = voucherUsageDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((recipient == null) ? 0 : recipient.hashCode());
		result = prime * result + ((voucher == null) ? 0 : voucher.hashCode());
		result = prime * result + ((voucherUsageDate == null) ? 0 : voucherUsageDate.hashCode());
		result = prime * result + (voucherUsedFlag ? 1231 : 1237);
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
		RecipientVoucher other = (RecipientVoucher) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (recipient == null) {
			if (other.recipient != null)
				return false;
		} else if (!recipient.equals(other.recipient))
			return false;
		if (voucher == null) {
			if (other.voucher != null)
				return false;
		} else if (!voucher.equals(other.voucher))
			return false;
		if (voucherUsageDate == null) {
			if (other.voucherUsageDate != null)
				return false;
		} else if (!voucherUsageDate.equals(other.voucherUsageDate))
			return false;
		if (voucherUsedFlag != other.voucherUsedFlag)
			return false;
		return true;
	}


		
}
