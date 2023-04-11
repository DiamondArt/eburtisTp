package com.eburtis.tp.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorVo {

  private Date timestamp = new Date();

  private Integer httpCode;

  private ErrorCodes code;

  private String message;
  private String path;

  private List<String> errors = new ArrayList<>();

}
