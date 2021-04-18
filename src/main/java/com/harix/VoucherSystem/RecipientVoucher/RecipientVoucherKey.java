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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecipientVoucherKey implements Serializable {

	@Column(name = "recipient_email")
	private String recipientEmail;
	
	@Column(name = "voucher_code")
	private int voucherCode;

	public RecipientVoucherKey() {
		
	}
			
	public RecipientVoucherKey(String recipientEmail, int voucherCode) {
		super();
		this.recipientEmail = recipientEmail;
		this.voucherCode = voucherCode;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public int getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(int voucherCode) {
		this.voucherCode = voucherCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recipientEmail == null) ? 0 : recipientEmail.hashCode());
		result = prime * result + voucherCode;
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
		RecipientVoucherKey other = (RecipientVoucherKey) obj;
		if (recipientEmail == null) {
			if (other.recipientEmail != null)
				return false;
		} else if (!recipientEmail.equals(other.recipientEmail))
			return false;
		if (voucherCode != other.voucherCode)
			return false;
		return true;
	}
	
	
}
