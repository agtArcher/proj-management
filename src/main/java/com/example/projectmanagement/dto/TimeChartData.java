package com.example.projectmanagement.dto;

import java.util.Date;

public interface TimeChartData {
    //dto uses every method, but somehow analysis instrument not understand it
    String getProjectName();
    Date getStartDate();
    Date getEndDate();
}
