package com.example.electronic;

public class PersonalData {
    private String member_time;
    private String member_charge;
    private String member_preserved;

    public String getMember_time() {
        return member_time;
    }

    public String getMember_charge() {
        return member_charge;
    }

    public String getMember_preserved() {
        return member_preserved;
    }

    public void setMember_time(String member_time) {

        member_time = member_time + "시";
        this.member_time = member_time;
    }

    public void setMember_charge(String member_charge) {

        member_charge = "1W당 " + member_charge + "원";
        this.member_charge = member_charge;
    }

    public void setMember_preserved(String member_preserved) {
        if(member_preserved.equals("0"))
        {
            this.member_preserved = "예약가능";
        }
        else
        {
            this.member_preserved = "예약불가";
        }
    }

}