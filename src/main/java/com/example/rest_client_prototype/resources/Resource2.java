package com.example.rest_client_prototype.resources;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * REST APIにて返却するリソースオブジェクト
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Resource2 {
	/** ID */
	private String id;
	/** 名前 */
	private String name;
	/** とある日付 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonInclude(JsonInclude.Include.NON_NULL) // nullのフィールドをJSONに含めない
	private LocalDate hogeDate;
}
