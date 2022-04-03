package com.poseidon.Interface;
public interface InvoiceService {

   public Optional<InvoiceVO> fetchInvoiceVOFromTagNo(String tagNo);
}