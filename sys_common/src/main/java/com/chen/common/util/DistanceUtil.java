package com.chen.common.util;

/**
 * 距离工具类
 **/
public class DistanceUtil {

    /**
     * 距离格式化
     * @param distance 距离，单位：米
     * @return 格式化后的文字
     */
    public static String formatDistance(Double distance) {
        if(distance == null){
            return "-- m";
        }

        if(distance.intValue() < 1000){
            return distance.intValue() + " m";
        }else if(distance.intValue() > 1000 && distance < 10000){
            return BigDecimalUtil.div(distance, 1000, 1) + " km";
        }else{
            return BigDecimalUtil.div(distance, 1000, 0) + "km";
        }
    }

    /**
     * 计算两点之间的经纬度计算直线距离
     * @param lon1 起点的经度
     * @param lat1 起点的纬度
     * @param lon2 终点的经度
     * @param lat2 终点的纬度
     * @return 直线距离，单位：米
     */
    public static double calculateDistance(double lon1, double lat1, double lon2, double lat2) {
        double earthRadius = 6371.393 * 1000; // 地球半径，单位：米

        double dLon = Math.toRadians(lon2 - lon1);
        double dLat = Math.toRadians(lat2 - lat1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

}
