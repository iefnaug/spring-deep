package com.itheima.a23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyDateFormatter2 implements Formatter<Date> {
    private static final Logger log = LoggerFactory.getLogger(MyDateFormatter2.class);
    private final String desc;

    public MyDateFormatter2(String desc) {
        this.desc = desc;
    }

    @Override
    public String print(Date date, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy||MM||dd");
        return sdf.format(date);
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        log.debug(">>>>>> 进入了: {}", desc);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy||MM||dd");
        return sdf.parse(text);
    }


}
