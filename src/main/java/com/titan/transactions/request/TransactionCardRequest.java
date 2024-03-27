package com.titan.transactions.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionCardRequest implements Serializable {

    private Double price;
    private Boolean withTip;
    private Double tip;
    private Boolean withCard;
    private String cardNumber;
    private Boolean paid;
    private Long userId;
}
