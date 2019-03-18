package com.example.wissdom.common.uitls.baidulocation;//package com.snxy.freshfood.common.uitls.baidulocation;
//
//import android.content.Context;
//
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.LocationClient;
//import com.baidu.location.LocationClientOption;
//import com.baidu.mapapi.map.BaiduMap;
//import com.baidu.mapapi.map.MapStatus;
//import com.baidu.mapapi.map.MapStatusUpdate;
//import com.baidu.mapapi.map.MapStatusUpdateFactory;
//import com.baidu.mapapi.model.LatLng;
//import com.baidu.mapapi.search.core.SearchResult;
//import com.baidu.mapapi.search.geocode.GeoCodeResult;
//import com.baidu.mapapi.search.geocode.GeoCoder;
//import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
//import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
//import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
//
///**
// * Create: 2018/9/14 9:05
// *
// * @author: Coco
// * Description: 百度地图定位
// * Version: 1.0
// **/
//@SuppressWarnings("unused")
//public class Location {
//
//    private LocationClient location;
//
//    /**
//     * 静态单例内部类
//     **/
//    private Location() {
//    }
//
//    public static Location getInstance() {
//        return Location2.t;
//    }
//
//    private static class Location2 {
//        private static Location t = new Location();
//    }
//
//    /**
//     * 发起定位
//     *
//     * @param context
//     * @param bdLocationListener
//     */
//    public void getLocation(Context context, int scanspan, BDLocationListener bdLocationListener) {
//        //TODO  定位
//        location = new LocationClient(context);
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
//        );
//        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        //可选，默认gcj02，设置返回的定位结果坐标系
//        option.setCoorType("bd09ll");
//        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        option.setScanSpan(scanspan);
//        //可选，设置是否需要地址信息，默认不需要
//        option.setIsNeedAddress(true);
//        //可选，默认false,设置是否使用gps
//        option.setOpenGps(true);
//        //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
//        option.setLocationNotify(false);
//        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        option.setIsNeedLocationDescribe(true);
//        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        option.setIsNeedLocationPoiList(true);
//        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.setIgnoreKillProcess(false);
//        //可选，默认false，设置是否收集CRASH信息，默认收集
//        option.SetIgnoreCacheException(true);
//        //可选，默认false，设置是否需要过滤gps仿真结果，默认需要
//        option.setEnableSimulateGps(true);
//        location.setLocOption(option);
//        location.registerLocationListener(bdLocationListener);
//        location.start();
//    }
//
//    public void stopLocation() {
//        //TODO 停止定位
//        if (null != location) {
//            location.stop();
//        }
//    }
//
//    /**
//     * 移动到指定位置
//     *
//     * @param center
//     */
//    public void move2CurrentPoint(LatLng center, BaiduMap baiduMap, int zoom) {
//        //TODO  移动到当前位置
//        MapStatus mMapStatus = new MapStatus.Builder().target(center).zoom(zoom).build();
//        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
//        if (null != baiduMap) {
//            baiduMap.setMapStatus(mMapStatusUpdate);
//        }
//    }
//
//    /**
//     * 逆向地理 转换
//     *
//     * @param latLng
//     * @param onGetCloudRgcResult
//     */
//    public void getAddressByLatLng(LatLng latLng, OnGetCloudRgcResult onGetCloudRgcResult) {
//        //经纬度转地址
//        GeoCoder geocode = GeoCoder.newInstance();
//        ReverseGeoCodeOption options = new ReverseGeoCodeOption().location(latLng);
//        geocode.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
//            @Override
//            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
//            }
//
//            @Override
//            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
//                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
//                    return;
//                }
//                //处理数据时，没有网络拿不到结果，注意非空判断
//                if (null != onGetCloudRgcResult) {
//                    onGetCloudRgcResult.onCloudRgcResult(reverseGeoCodeResult);
//                }
//            }
//        });
//        // 发起反地理编码请求
//        geocode.reverseGeoCode(options);
//    }
//
//    public interface OnGetCloudRgcResult {
//        /**
//         * 获取经纬度对象回调
//         * @param reverseGeoCodeResult
//         */
//        void onCloudRgcResult(ReverseGeoCodeResult reverseGeoCodeResult);
//    }
//}
