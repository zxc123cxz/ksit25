package com.kaisheng.entity;

import java.util.List;

public class Member {
        private int id ;
        private String nickname;
        private String name;
        private String email;

        private String bookname;
        private List<Order> memberList;
        @Override
        public String toString() {
                return "Member{" +
                        "id=" + id +
                        ", nickname='" + nickname + '\'' +
                        ", name='" + name + '\'' +
                        ", email='" + email + '\'' +

                        ", address='" + address + '\'' +
                        ", tel='" + tel + '\'' +
                        ", zip='" + zip + '\'' +
                        ", password='" + password + '\'' +
                        ", memberList=" + memberList +
                        '}';
        }

        public List<Order> getMemberList() {
                return memberList;
        }

        public void setMemberList(List<Order> memberList) {
                this.memberList = memberList;

        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getNickname() {
                return nickname;
        }

        public void setNickname(String nickname) {
                this.nickname = nickname;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getTel() {
                return tel;
        }

        public void setTel(String tel) {
                this.tel = tel;
        }

        public String getZip() {
                return zip;
        }

        public void setZip(String zip) {
                this.zip = zip;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        private String address;
        private String tel;
        private String zip;
        private String password;

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
