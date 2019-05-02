package com.example.hugh.life.api.result;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Data
public class RecommendResult implements Serializable {
    List<String> contentList;
}
