package com.sh.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private String name; // 컬럼명
    private Object value; // 검색값
    private Object start;
    private Object end;

}
