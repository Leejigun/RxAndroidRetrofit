package com.example.dlwlr.rxandroidretrofit.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bus {

    @SerializedName("RESULT")
    @Expose
    private Result result;

    @SerializedName("BUSSTOP_LIST")
    @Expose
    private List<BusStopList> busStopListList = null;

    @SerializedName("ROW_COUNT")
    @Expose
    private Integer rowCount;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<BusStopList> getBusStopListList() {
        return busStopListList;
    }

    public void setBusStopListList(List<BusStopList> busStopListList) {
        this.busStopListList = busStopListList;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public class Result {

        @SerializedName("RESULT_MSG")
        @Expose
        private String resultMsg;
        @SerializedName("RESULT_CODE")
        @Expose
        private String resultCode;
        // MARK: - Getter && Setter
        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }
    }


    public class BusStopList {

        @SerializedName("ARRIVE")
        @Expose
        private String arrive;
        @SerializedName("REMAIN_MIN")
        @Expose
        private Integer remainMin;
        @SerializedName("ENG_BUSSTOP_NAME")
        @Expose
        private String engBusStopName;
        @SerializedName("REMAIN_STOP")
        @Expose
        private Integer remainStop;
        @SerializedName("DIR_START")
        @Expose
        private String dirStart;
        @SerializedName("BUS_ID")
        @Expose
        private String busId;
        @SerializedName("BUSSTOP_NAME")
        @Expose
        private String busStopName;
        @SerializedName("DIR_END")
        @Expose
        private String dirEnd;
        @SerializedName("DIR_PASS")
        @Expose
        private String dirPass;
        @SerializedName("LOW_BUS")
        @Expose
        private Integer lowBus;
        @SerializedName("CURR_STOP_ID")
        @Expose
        private Integer currStopId;
        @SerializedName("ARRIVE_FLAG")
        @Expose
        private Integer arriveFlag;
        @SerializedName("LINE_ID")
        @Expose
        private Integer lineID;
        @SerializedName("LINE_NAME")
        @Expose
        private String lineName;

        public String getARRIVE() {
            return arrive;
        }

        public void setARRIVE(String aRRIVE) {
            this.arrive = aRRIVE;
        }

        public Integer getREMAINMIN() {
            return remainMin;
        }

        public void setREMAINMIN(Integer rEMAINMIN) {
            this.remainMin = rEMAINMIN;
        }

        public String getENGBUSSTOPNAME() {
            return engBusStopName;
        }

        public void setENGBUSSTOPNAME(String eNGBUSSTOPNAME) {
            this.engBusStopName = eNGBUSSTOPNAME;
        }

        public Integer getREMAINSTOP() {
            return remainStop;
        }

        public void setREMAINSTOP(Integer rEMAINSTOP) {
            this.remainStop = rEMAINSTOP;
        }

        public String getDIRSTART() {
            return dirStart;
        }

        public void setDIRSTART(String dIRSTART) {
            this.dirStart = dIRSTART;
        }

        public String getBUSID() {
            return busId;
        }

        public void setBUSID(String bUSID) {
            this.busId = bUSID;
        }

        public String getBUSSTOPNAME() {
            return busStopName;
        }

        public void setBUSSTOPNAME(String bUSSTOPNAME) {
            this.busStopName = bUSSTOPNAME;
        }

        public String getDIREND() {
            return dirEnd;
        }

        public void setDIREND(String dIREND) {
            this.dirEnd = dIREND;
        }

        public String getDIRPASS() {
            return dirPass;
        }

        public void setDIRPASS(String dIRPASS) {
            this.dirPass = dIRPASS;
        }

        public Integer getLOWBUS() {
            return lowBus;
        }

        public void setLOWBUS(Integer lOWBUS) {
            this.lowBus = lOWBUS;
        }

        public Integer getCURRSTOPID() {
            return currStopId;
        }

        public void setCURRSTOPID(Integer cURRSTOPID) {
            this.currStopId = cURRSTOPID;
        }

        public Integer getARRIVEFLAG() {
            return arriveFlag;
        }

        public void setARRIVEFLAG(Integer aRRIVEFLAG) {
            this.arriveFlag = aRRIVEFLAG;
        }

        public Integer getLINEID() {
            return lineID;
        }

        public void setLINEID(Integer lINEID) {
            this.lineID = lINEID;
        }

        public String getLINENAME() {
            return lineName;
        }

        public void setLINENAME(String lINENAME) {
            this.lineName = lINENAME;
        }

    }
}