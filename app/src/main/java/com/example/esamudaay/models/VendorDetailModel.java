package com.example.esamudaay.models;

import java.util.ArrayList;

public class VendorDetailModel {
    String skuid;
    String productname;
    String businessname;
    ArrayList<String>  failurereasons;

    public VendorDetailModel() {
    }

    public VendorDetailModel(String skuid, String productname, String businessname, ArrayList<String> failurereasons) {
        this.skuid = skuid;
        this.productname = productname;
        this.businessname = businessname;
        this.failurereasons = failurereasons;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public ArrayList<String> getFailurereasons() {
        return failurereasons;
    }

    public void setFailurereasons(ArrayList<String> failurereasons) {
        this.failurereasons = failurereasons;
    }
}
