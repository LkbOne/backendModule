package com.example.hugh.life.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetVerificationCodeResult implements Serializable {
    String code;
}
