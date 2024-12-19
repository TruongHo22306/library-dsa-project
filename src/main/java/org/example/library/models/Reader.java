package org.example.library.models;

public class Reader {
    private String readerId;
    private String readerName;
    private String readerAddress;
    private String phoneNumber;
    private String dob;
    private String image;

    public Reader() {
    }

    public Reader(String readerId, String readerName, String readerAddress, String phoneNumber, String dob, String image) {
        this.readerId = readerId;
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.image = image;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderAddress() {
        return readerAddress;
    }

    public void setReaderAddress(String readerAddress) {
        this.readerAddress = readerAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.join(",", readerId, readerName, readerAddress, phoneNumber, dob, image);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String readerId;
        private String readerName;
        private String readerAddress;
        private String phoneNumber;
        private String dob;
        private String image;

        public Builder readerId(String readerId) {
            this.readerId = readerId;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder readerName(String readerName) {
            this.readerName = readerName;
            return this;
        }

        public Builder readerAddress(String readerAddress) {
            this.readerAddress = readerAddress;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder dob(String dob) {
            this.dob = dob;
            return this;
        }

        public Reader build() {
            return new Reader(readerId, readerName, readerAddress, phoneNumber, dob, image);
        }
    }
}
