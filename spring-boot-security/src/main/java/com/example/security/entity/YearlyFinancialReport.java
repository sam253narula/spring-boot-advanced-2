package com.example.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YearlyFinancialReport {

    @Id
    private Integer id;

	@Column(name = "YEARS")
    private String year;

	@Column(name = "NET_REVENUE")
	private int netRevenue;

	@Column(name = "MATERIAL_COST")
	private int materialCost;

	@Column(name = "NET_PROFIT")
	private int netProfit;
}
