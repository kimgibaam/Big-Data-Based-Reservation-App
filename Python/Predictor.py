#!/usr/bin/env python
# coding: utf-8

import sys
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import datetime
from sklearn.linear_model import LinearRegression, LogisticRegression
from sklearn.model_selection import cross_val_score, train_test_split
from datetime import datetime

sys.stdout = open('output.txt','w')
now = datetime.now()

year = now.year
month = now.month

power_data = pd.read_excel('C:/Bitnami/wampstack-7.3.10-0/apache2/htdocs/python/data/주택소비계수2018.xlsx')

tim = pd.get_dummies(power_data['시간'],prefix = 'tim_')
power = pd.concat([power_data,tim], axis =1)
power.drop('시간',1,inplace = True)

day = pd.get_dummies(power_data['요일'],prefix = 'day_')
power2 = pd.concat([power,day], axis =1)
power2.drop('요일',1,inplace = True)

mth = pd.get_dummies(power_data['월'],prefix = 'mth_')
power3 = pd.concat([power2,mth], axis =1)
power3.drop('월',1,inplace = True)

y = power3["전력소비계수"]

X = power3.drop('전력소비계수', axis =1)

X_train, X_test, y_train, y_test = train_test_split(X,y,test_size=0.2)
model = LinearRegression()
model.fit(X_train, y_train)

def prediction(x1,x2,x3):

    if x2 == ( 1 or 2 or 3 or 4 ):
        d = 25
    elif x2 == 0:
        d = 26
    elif x2 == 6:
        d = 27
    else:
        d = 28

    # 0:월, 1:화, 2:수, 3:목, 4:금, 5:토, 6:일

    i = x3 + 28
    y1 = (model.coef_[0]*x1 + model.coef_[11] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y2 = (model.coef_[0]*x1 + model.coef_[17] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y3 = (model.coef_[0]*x1 + model.coef_[18] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y4 = (model.coef_[0]*x1 + model.coef_[19] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y5 = (model.coef_[0]*x1 + model.coef_[20] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y6 = (model.coef_[0]*x1 + model.coef_[21] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y7 = (model.coef_[0]*x1 + model.coef_[22] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y8 = (model.coef_[0]*x1 + model.coef_[23] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y9 = (model.coef_[0]*x1 + model.coef_[24] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y10 = (model.coef_[0]*x1 + model.coef_[1] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y11 = (model.coef_[0]*x1 + model.coef_[2] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y12 = (model.coef_[0]*x1 + model.coef_[3] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y13 = (model.coef_[0]*x1 + model.coef_[4] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y14 = (model.coef_[0]*x1 + model.coef_[5] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y15 = (model.coef_[0]*x1 + model.coef_[6] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y16 = (model.coef_[0]*x1 + model.coef_[7] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y17 = (model.coef_[0]*x1 + model.coef_[8] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y18 = (model.coef_[0]*x1 + model.coef_[9] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y19 = (model.coef_[0]*x1 + model.coef_[10] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y20 = (model.coef_[0]*x1 + model.coef_[12] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y21 = (model.coef_[0]*x1 + model.coef_[13] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y22 = (model.coef_[0]*x1 + model.coef_[14] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y23 = (model.coef_[0]*x1 + model.coef_[15] + model.coef_[d] + model.coef_[i] + model.intercept_)
    y24 = (model.coef_[0]*x1 + model.coef_[16] + model.coef_[d] + model.coef_[i] + model.intercept_)

    a1 = (((y1-646)*(232.5-52.5))/(1405-646))+ 52.5
    a2 = (((y2-646)*(232.5-52.5))/(1405-646))+ 52.5
    a3 = (((y3-646)*(232.5-52.5))/(1405-646))+ 52.5
    a4 = (((y4-646)*(232.5-52.5))/(1405-646))+ 52.5
    a5 = (((y5-646)*(232.5-52.5))/(1405-646))+ 52.5
    a6 = (((y6-646)*(232.5-52.5))/(1405-646))+ 52.5
    a7 = (((y7-646)*(232.5-52.5))/(1405-646))+ 52.5
    a8 = (((y8-646)*(232.5-52.5))/(1405-646))+ 52.5
    a9 = (((y9-646)*(232.5-52.5))/(1405-646))+ 52.5
    a10 = (((y10-646)*(232.5-52.5))/(1405-646))+ 52.5
    a11 = (((y11-646)*(232.5-52.5))/(1405-646))+ 52.5
    a12 = (((y12-646)*(232.5-52.5))/(1405-646))+ 52.5
    a13 = (((y13-646)*(232.5-52.5))/(1405-646))+ 52.5
    a14 = (((y14-646)*(232.5-52.5))/(1405-646))+ 52.5
    a15 = (((y15-646)*(232.5-52.5))/(1405-646))+ 52.5
    a16 = (((y16-646)*(232.5-52.5))/(1405-646))+ 52.5
    a17 = (((y17-646)*(232.5-52.5))/(1405-646))+ 52.5
    a18 = (((y18-646)*(232.5-52.5))/(1405-646))+ 52.5
    a19 = (((y19-646)*(232.5-52.5))/(1405-646))+ 52.5
    a20 = (((y20-646)*(232.5-52.5))/(1405-646))+ 52.5
    a21 = (((y21-646)*(232.5-52.5))/(1405-646))+ 52.5
    a22 = (((y22-646)*(232.5-52.5))/(1405-646))+ 52.5
    a23 = (((y23-646)*(232.5-52.5))/(1405-646))+ 52.5
    a24 = (((y24-646)*(232.5-52.5))/(1405-646))+ 52.5


    print("%d"%a24,"%d"%a1,"%d"%a2,"%d"%a3,"%d"%a4,"%d"%a5,"%d"%a6,"%d"%a7,"%d"%a8,"%d"%a9,"%d"%a10,"%d"%a11,"%d"%a12,"%d"%a13,"%d"%a14,"%d"%a15,"%d"%a16,"%d"%a17,"%d"%a18,"%d"%a19,"%d"%a20,"%d"%a21,"%d"%a22,"%d"%a23)

prediction(now.year-1,datetime.today().weekday(),now.month)
