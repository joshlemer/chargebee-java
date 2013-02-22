package com.chargebee.models;

import com.chargebee.*;
import com.chargebee.internal.*;
import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.models.enums.*;
import org.json.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Invoice extends Resource<Invoice> {

    public enum Status {
        PAID,
        PAYMENT_DUE,
        NOT_PAID,
        PENDING,
        _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
        java-client version incompatibility. We suggest you to upgrade to the latest version */
    }

    public static class LineItem extends Resource<LineItem> {
        public LineItem(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Timestamp dateFrom() {
            return reqTimestamp("date_from");
        }

        public Timestamp dateTo() {
            return reqTimestamp("date_to");
        }

        public Integer unitAmount() {
            return reqInteger("unit_amount");
        }

        public Integer quantity() {
            return optInteger("quantity");
        }

        public Integer tax() {
            return optInteger("tax");
        }

        public Integer amount() {
            return reqInteger("amount");
        }

        public String description() {
            return reqString("description");
        }

    }

    public static class Discount extends Resource<Discount> {
        public Discount(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Integer amount() {
            return reqInteger("amount");
        }

        public String description() {
            return optString("description");
        }

    }

    public static class Tax extends Resource<Tax> {
        public Tax(JSONObject jsonObj) {
            super(jsonObj);
        }

        public Integer amount() {
            return reqInteger("amount");
        }

        public String description() {
            return optString("description");
        }

    }

    //Constructors
    //============

    public Invoice(String jsonStr) {
        super(jsonStr);
    }

    public Invoice(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String id() {
        return reqString("id");
    }

    public String subscriptionId() {
        return reqString("subscription_id");
    }

    public Boolean recurring() {
        return reqBoolean("recurring");
    }

    public Status status() {
        return reqEnum("status", Status.class);
    }

    public String vatNumber() {
        return optString("vat_number");
    }

    public Timestamp startDate() {
        return reqTimestamp("start_date");
    }

    public Timestamp endDate() {
        return optTimestamp("end_date");
    }

    public Integer amount() {
        return optInteger("amount");
    }

    public Timestamp paidOn() {
        return optTimestamp("paid_on");
    }

    public Timestamp nextRetry() {
        return optTimestamp("next_retry");
    }

    public Integer subTotal() {
        return reqInteger("sub_total");
    }

    public Integer tax() {
        return reqInteger("tax");
    }

    public List<Invoice.LineItem> lineItems() {
        return optList("line_items", Invoice.LineItem.class);
    }

    public List<Invoice.Discount> discounts() {
        return optList("discounts", Invoice.Discount.class);
    }

    public List<Invoice.Tax> taxes() {
        return optList("taxes", Invoice.Tax.class);
    }

    // Operations
    //===========

    public static AddChargeRequest addCharge(String id) throws IOException {
        String url = url("invoices", nullCheck(id), "add_charge");
        return new AddChargeRequest(Method.POST, url);
    }

    public static AddAddonChargeRequest addAddonCharge(String id) throws IOException {
        String url = url("invoices", nullCheck(id), "add_addon_charge");
        return new AddAddonChargeRequest(Method.POST, url);
    }

    public static ListRequest list() throws IOException {
        String url = url("invoices");
        return new ListRequest(url);
    }

    public static ListRequest invoicesForSubscription(String id) throws IOException {
        String url = url("subscriptions", nullCheck(id), "invoices");
        return new ListRequest(url);
    }

    public static Request retrieve(String id) throws IOException {
        String url = url("invoices", nullCheck(id));
        return new Request(Method.GET, url);
    }

    public static Request collect(String id) throws IOException {
        String url = url("invoices", nullCheck(id), "collect");
        return new Request(Method.POST, url);
    }

    public static ChargeRequest charge() throws IOException {
        String url = url("invoices", "charge");
        return new ChargeRequest(Method.POST, url);
    }

    public static ChargeAddonRequest chargeAddon() throws IOException {
        String url = url("invoices", "charge_addon");
        return new ChargeAddonRequest(Method.POST, url);
    }


    // Operation Request Classes
    //==========================

    public static class AddChargeRequest extends Request {

        private AddChargeRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }
    
        public AddChargeRequest amount(Integer amount) {
            params.add("amount", amount);
            return this;
        }

        public AddChargeRequest description(String description) {
            params.add("description", description);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class AddAddonChargeRequest extends Request {

        private AddAddonChargeRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }
    
        public AddAddonChargeRequest addonId(String addonId) {
            params.add("addon_id", addonId);
            return this;
        }

        public AddAddonChargeRequest addonQuantity(Integer addonQuantity) {
            params.addOpt("addon_quantity", addonQuantity);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ChargeRequest extends Request {

        private ChargeRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }
    
        public ChargeRequest subscriptionId(String subscriptionId) {
            params.add("subscription_id", subscriptionId);
            return this;
        }

        public ChargeRequest amount(Integer amount) {
            params.add("amount", amount);
            return this;
        }

        public ChargeRequest description(String description) {
            params.add("description", description);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

    public static class ChargeAddonRequest extends Request {

        private ChargeAddonRequest(Method httpMeth, String url) {
            super(httpMeth, url);
        }
    
        public ChargeAddonRequest subscriptionId(String subscriptionId) {
            params.add("subscription_id", subscriptionId);
            return this;
        }

        public ChargeAddonRequest addonId(String addonId) {
            params.add("addon_id", addonId);
            return this;
        }

        public ChargeAddonRequest addonQuantity(Integer addonQuantity) {
            params.addOpt("addon_quantity", addonQuantity);
            return this;
        }

        @Override
        public Params params() {
            return params;
        }
    }

}
