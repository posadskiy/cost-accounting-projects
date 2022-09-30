package com.posadskiy.costaccounting.projects.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Purchase extends MoneyAction {}
