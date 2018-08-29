package com.sky.param;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Administrator on 2018/8/28.
 */
@Getter
@Setter
public class TestVo {
    @NotNull
    private String msg;


    @NotNull
    private Integer id;

    @NotEmpty
    private List<String> str;


}
