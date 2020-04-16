package com.tttn.spring.webshop.service;

import com.tttn.spring.webshop.Crud.PhieuMuaCrud;
import com.tttn.spring.webshop.convert.TotalConvert;
import com.tttn.spring.webshop.dto.DashboardTotal;
import com.tttn.spring.webshop.model.PhieumuaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.tttn.spring.webshop.convert.TotalConvert.*;

@Service
public class PhieuMuaService {

    public static final Logger LOG= LoggerFactory.getLogger(PhieuMuaService.class);

    @Autowired
    PhieuMuaCrud phieuMuaCrud;

    public ServiceRestfull findStatus(int status){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setData(phieuMuaCrud.getListFindStatus(status));
        return serviceRestfull;
    }

    public ServiceRestfull update(int status,String maPhieu){
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        PhieumuaEntity phieumuaEntity=phieuMuaCrud.findById(maPhieu).orElse(null);
        phieumuaEntity.setStatus(status+1);
        phieuMuaCrud.save(phieumuaEntity);
        serviceRestfull=findStatus(status);
        serviceRestfull.setMessage("đã xác nhận đơn hàng");
        return serviceRestfull;

    }

    public ServiceRestfull dashboardTotal() throws ParseException {

        List<DashboardTotal> listTotal=new ArrayList<>();
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        int year = today.getYear();
        String monthstr=String.valueOf(month);
        if(month<10){
            monthstr ="0"+month;
        }
        for (int i = 1; i <= day; i++) {
            String daystr = String.valueOf(i);
            String nextDay = String.valueOf(i + 1);
            if (i + 1 < 10) {
                nextDay = "0" + nextDay;
            }
            if (i < 10) {
                daystr = "0" + i;
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String ngay = year + "-" + monthstr + "-" + daystr;
            String next = year + "-" + monthstr + "-" + nextDay;

            Date startdate = format.parse(ngay);
            Date nextdate = format.parse(next);
            LOG.info("start date : {}", format.format(startdate));
            LOG.info("close date : {}", format.format(nextdate));
            List<PhieumuaEntity> list = phieuMuaCrud.getListFindDate(startdate, nextdate);
            DashboardTotal total=getConvert(ngay,list);
            listTotal.add(total);
        }
        ServiceRestfull serviceRestfull=new ServiceRestfull();
        serviceRestfull.setMessage("total dashboard ");
        serviceRestfull.setData(listTotal);
        return serviceRestfull;
    }
}
