package com.policyboss.policybosscaller.data.db.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.policyboss.policybosscaller.data.db.dao.ConstantDao;
import com.policyboss.policybosscaller.data.db.dao.ConstantDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CallerDatabase_Impl extends CallerDatabase {
  private volatile ConstantDao _constantDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ConstantEntity` (`FBAId` TEXT NOT NULL, `AddPospVisible` TEXT NOT NULL, `CVUrl` TEXT NOT NULL, `ERPID` TEXT NOT NULL, `EliteKotakUrl` TEXT NOT NULL, `FinboxEnabled` TEXT NOT NULL, `FinperksEnabled` TEXT NOT NULL, `FourWheelerEnabled` TEXT NOT NULL, `FourWheelerUrl` TEXT NOT NULL, `FullName` TEXT NOT NULL, `GenerateMotorLeadsEnabled` TEXT NOT NULL, `HealthDemoUrl` TEXT NOT NULL, `HealthPopup` TEXT NOT NULL, `InvestmentEnabled` TEXT NOT NULL, `InvestmentUrl` TEXT NOT NULL, `IsDynamicDashEnabled` TEXT NOT NULL, `KotakEliteEnabled` TEXT NOT NULL, `LeadDashUrl` TEXT NOT NULL, `LoginID` TEXT NOT NULL, `ManagName` TEXT NOT NULL, `MangEmail` TEXT NOT NULL, `MangMobile` TEXT NOT NULL, `MyMessagesEnabled` TEXT NOT NULL, `MyTransactionsEnabled` TEXT NOT NULL, `PBByCrnSearch` TEXT NOT NULL, `POSPNo` TEXT NOT NULL, `POSP_STATUS` TEXT NOT NULL, `PospappformEnabled` TEXT NOT NULL, `PospletterEnabled` TEXT NOT NULL, `RaiseTickitEnabled` TEXT NOT NULL, `RaiseTickitUrl` TEXT NOT NULL, `SmsTemplatesEnabled` TEXT NOT NULL, `Status` TEXT NOT NULL, `SuppEmail` TEXT NOT NULL, `SuppMobile` TEXT NOT NULL, `TermPopup` TEXT NOT NULL, `TermPopupurl` TEXT NOT NULL, `TwoWheelerEnabled` TEXT NOT NULL, `TwoWheelerUrl` TEXT NOT NULL, `addposplimit` TEXT NOT NULL, `androidproversion` TEXT NOT NULL, `boempuid` TEXT NOT NULL, `cobrowserisactive` TEXT NOT NULL, `cobrowserlicensecode` TEXT NOT NULL, `crnmobileno` TEXT NOT NULL, `emplat` TEXT NOT NULL, `emplng` TEXT NOT NULL, `enableInsuranceBusiness` TEXT NOT NULL, `enableenrolasposp` TEXT NOT NULL, `enablemyaccountupdate` TEXT NOT NULL, `enablencd` TEXT NOT NULL, `enablesynccontact` TEXT NOT NULL, `fba_campaign_id` TEXT NOT NULL, `fba_campaign_name` TEXT NOT NULL, `fba_uid` TEXT NOT NULL, `finboxurl` TEXT NOT NULL, `finmartwhatsappno` TEXT NOT NULL, `finperkurl` TEXT NOT NULL, `hdfc_code` TEXT NOT NULL, `healthurl` TEXT NOT NULL, `healthurltemp` TEXT NOT NULL, `insurancerepositorylink` TEXT NOT NULL, `iosuid` TEXT NOT NULL, `iosversion` TEXT NOT NULL, `isEmployee` TEXT NOT NULL, `loanparentdesignation` TEXT NOT NULL, `loanparentemail` TEXT NOT NULL, `loanparentid` TEXT NOT NULL, `loanparentmobile` TEXT NOT NULL, `loanparentname` TEXT NOT NULL, `loanparentphoto` TEXT NOT NULL, `loanselfdesignation` TEXT NOT NULL, `loanselfemail` TEXT NOT NULL, `loanselfid` TEXT NOT NULL, `loanselfmobile` TEXT NOT NULL, `loanselfname` TEXT NOT NULL, `loanselfphoto` TEXT NOT NULL, `loansenddesignation` TEXT NOT NULL, `loansendemail` TEXT NOT NULL, `loansendid` TEXT NOT NULL, `loansendmobile` TEXT NOT NULL, `loansendname` TEXT NOT NULL, `loansendphoto` TEXT NOT NULL, `marketinghomedesciption` TEXT NOT NULL, `marketinghomeenabled` TEXT NOT NULL, `marketinghomemaxcount` TEXT NOT NULL, `marketinghomepopupid` TEXT NOT NULL, `marketinghometitle` TEXT NOT NULL, `marketinghometransfertype` TEXT NOT NULL, `marketinghomeurl` TEXT NOT NULL, `messagesender` TEXT NOT NULL, `notificationpopupurl` TEXT NOT NULL, `notificationpopupurltype` TEXT NOT NULL, `paenable` TEXT NOT NULL, `parentid` TEXT NOT NULL, `plactive` TEXT NOT NULL, `plbanner` TEXT NOT NULL, `policyByCRNEnabled` TEXT NOT NULL, `pospparentdesignation` TEXT NOT NULL, `pospparentemail` TEXT NOT NULL, `pospparentid` TEXT NOT NULL, `pospparentmobile` TEXT NOT NULL, `pospparentname` TEXT NOT NULL, `pospparentphoto` TEXT NOT NULL, `pospselfdesignation` TEXT NOT NULL, `pospselfemail` TEXT NOT NULL, `pospselfid` TEXT NOT NULL, `pospselfmobile` TEXT NOT NULL, `pospselfname` TEXT NOT NULL, `pospselfphoto` TEXT NOT NULL, `pospsenddesignation` TEXT NOT NULL, `pospsendemail` TEXT NOT NULL, `pospsendid` TEXT NOT NULL, `pospsendmobile` TEXT NOT NULL, `pospsendname` TEXT NOT NULL, `pospsendphoto` TEXT NOT NULL, `serviceurl` TEXT NOT NULL, `showmyinsurancebusiness` TEXT NOT NULL, `uid` TEXT NOT NULL, `ultralakshyaenabled` INTEGER NOT NULL, `userid` TEXT NOT NULL, PRIMARY KEY(`FBAId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '25118257a0bd454e994fce71c94e2740')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `ConstantEntity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsConstantEntity = new HashMap<String, TableInfo.Column>(121);
        _columnsConstantEntity.put("FBAId", new TableInfo.Column("FBAId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("AddPospVisible", new TableInfo.Column("AddPospVisible", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("CVUrl", new TableInfo.Column("CVUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("ERPID", new TableInfo.Column("ERPID", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("EliteKotakUrl", new TableInfo.Column("EliteKotakUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("FinboxEnabled", new TableInfo.Column("FinboxEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("FinperksEnabled", new TableInfo.Column("FinperksEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("FourWheelerEnabled", new TableInfo.Column("FourWheelerEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("FourWheelerUrl", new TableInfo.Column("FourWheelerUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("FullName", new TableInfo.Column("FullName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("GenerateMotorLeadsEnabled", new TableInfo.Column("GenerateMotorLeadsEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("HealthDemoUrl", new TableInfo.Column("HealthDemoUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("HealthPopup", new TableInfo.Column("HealthPopup", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("InvestmentEnabled", new TableInfo.Column("InvestmentEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("InvestmentUrl", new TableInfo.Column("InvestmentUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("IsDynamicDashEnabled", new TableInfo.Column("IsDynamicDashEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("KotakEliteEnabled", new TableInfo.Column("KotakEliteEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("LeadDashUrl", new TableInfo.Column("LeadDashUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("LoginID", new TableInfo.Column("LoginID", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("ManagName", new TableInfo.Column("ManagName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("MangEmail", new TableInfo.Column("MangEmail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("MangMobile", new TableInfo.Column("MangMobile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("MyMessagesEnabled", new TableInfo.Column("MyMessagesEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("MyTransactionsEnabled", new TableInfo.Column("MyTransactionsEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("PBByCrnSearch", new TableInfo.Column("PBByCrnSearch", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("POSPNo", new TableInfo.Column("POSPNo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("POSP_STATUS", new TableInfo.Column("POSP_STATUS", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("PospappformEnabled", new TableInfo.Column("PospappformEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("PospletterEnabled", new TableInfo.Column("PospletterEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("RaiseTickitEnabled", new TableInfo.Column("RaiseTickitEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("RaiseTickitUrl", new TableInfo.Column("RaiseTickitUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("SmsTemplatesEnabled", new TableInfo.Column("SmsTemplatesEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("Status", new TableInfo.Column("Status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("SuppEmail", new TableInfo.Column("SuppEmail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("SuppMobile", new TableInfo.Column("SuppMobile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("TermPopup", new TableInfo.Column("TermPopup", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("TermPopupurl", new TableInfo.Column("TermPopupurl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("TwoWheelerEnabled", new TableInfo.Column("TwoWheelerEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("TwoWheelerUrl", new TableInfo.Column("TwoWheelerUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("addposplimit", new TableInfo.Column("addposplimit", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("androidproversion", new TableInfo.Column("androidproversion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("boempuid", new TableInfo.Column("boempuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("cobrowserisactive", new TableInfo.Column("cobrowserisactive", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("cobrowserlicensecode", new TableInfo.Column("cobrowserlicensecode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("crnmobileno", new TableInfo.Column("crnmobileno", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("emplat", new TableInfo.Column("emplat", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("emplng", new TableInfo.Column("emplng", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("enableInsuranceBusiness", new TableInfo.Column("enableInsuranceBusiness", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("enableenrolasposp", new TableInfo.Column("enableenrolasposp", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("enablemyaccountupdate", new TableInfo.Column("enablemyaccountupdate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("enablencd", new TableInfo.Column("enablencd", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("enablesynccontact", new TableInfo.Column("enablesynccontact", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("fba_campaign_id", new TableInfo.Column("fba_campaign_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("fba_campaign_name", new TableInfo.Column("fba_campaign_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("fba_uid", new TableInfo.Column("fba_uid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("finboxurl", new TableInfo.Column("finboxurl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("finmartwhatsappno", new TableInfo.Column("finmartwhatsappno", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("finperkurl", new TableInfo.Column("finperkurl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("hdfc_code", new TableInfo.Column("hdfc_code", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("healthurl", new TableInfo.Column("healthurl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("healthurltemp", new TableInfo.Column("healthurltemp", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("insurancerepositorylink", new TableInfo.Column("insurancerepositorylink", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("iosuid", new TableInfo.Column("iosuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("iosversion", new TableInfo.Column("iosversion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("isEmployee", new TableInfo.Column("isEmployee", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanparentdesignation", new TableInfo.Column("loanparentdesignation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanparentemail", new TableInfo.Column("loanparentemail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanparentid", new TableInfo.Column("loanparentid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanparentmobile", new TableInfo.Column("loanparentmobile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanparentname", new TableInfo.Column("loanparentname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanparentphoto", new TableInfo.Column("loanparentphoto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanselfdesignation", new TableInfo.Column("loanselfdesignation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanselfemail", new TableInfo.Column("loanselfemail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanselfid", new TableInfo.Column("loanselfid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanselfmobile", new TableInfo.Column("loanselfmobile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanselfname", new TableInfo.Column("loanselfname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loanselfphoto", new TableInfo.Column("loanselfphoto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loansenddesignation", new TableInfo.Column("loansenddesignation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loansendemail", new TableInfo.Column("loansendemail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loansendid", new TableInfo.Column("loansendid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loansendmobile", new TableInfo.Column("loansendmobile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loansendname", new TableInfo.Column("loansendname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("loansendphoto", new TableInfo.Column("loansendphoto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("marketinghomedesciption", new TableInfo.Column("marketinghomedesciption", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("marketinghomeenabled", new TableInfo.Column("marketinghomeenabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("marketinghomemaxcount", new TableInfo.Column("marketinghomemaxcount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("marketinghomepopupid", new TableInfo.Column("marketinghomepopupid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("marketinghometitle", new TableInfo.Column("marketinghometitle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("marketinghometransfertype", new TableInfo.Column("marketinghometransfertype", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("marketinghomeurl", new TableInfo.Column("marketinghomeurl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("messagesender", new TableInfo.Column("messagesender", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("notificationpopupurl", new TableInfo.Column("notificationpopupurl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("notificationpopupurltype", new TableInfo.Column("notificationpopupurltype", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("paenable", new TableInfo.Column("paenable", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("parentid", new TableInfo.Column("parentid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("plactive", new TableInfo.Column("plactive", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("plbanner", new TableInfo.Column("plbanner", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("policyByCRNEnabled", new TableInfo.Column("policyByCRNEnabled", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospparentdesignation", new TableInfo.Column("pospparentdesignation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospparentemail", new TableInfo.Column("pospparentemail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospparentid", new TableInfo.Column("pospparentid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospparentmobile", new TableInfo.Column("pospparentmobile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospparentname", new TableInfo.Column("pospparentname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospparentphoto", new TableInfo.Column("pospparentphoto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospselfdesignation", new TableInfo.Column("pospselfdesignation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospselfemail", new TableInfo.Column("pospselfemail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospselfid", new TableInfo.Column("pospselfid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospselfmobile", new TableInfo.Column("pospselfmobile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospselfname", new TableInfo.Column("pospselfname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospselfphoto", new TableInfo.Column("pospselfphoto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospsenddesignation", new TableInfo.Column("pospsenddesignation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospsendemail", new TableInfo.Column("pospsendemail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospsendid", new TableInfo.Column("pospsendid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospsendmobile", new TableInfo.Column("pospsendmobile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospsendname", new TableInfo.Column("pospsendname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("pospsendphoto", new TableInfo.Column("pospsendphoto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("serviceurl", new TableInfo.Column("serviceurl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("showmyinsurancebusiness", new TableInfo.Column("showmyinsurancebusiness", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("uid", new TableInfo.Column("uid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("ultralakshyaenabled", new TableInfo.Column("ultralakshyaenabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstantEntity.put("userid", new TableInfo.Column("userid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysConstantEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesConstantEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoConstantEntity = new TableInfo("ConstantEntity", _columnsConstantEntity, _foreignKeysConstantEntity, _indicesConstantEntity);
        final TableInfo _existingConstantEntity = TableInfo.read(_db, "ConstantEntity");
        if (! _infoConstantEntity.equals(_existingConstantEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "ConstantEntity(com.policyboss.policybosscaller.data.model.DashboardData.ConstantEntity).\n"
                  + " Expected:\n" + _infoConstantEntity + "\n"
                  + " Found:\n" + _existingConstantEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "25118257a0bd454e994fce71c94e2740", "5fa48f206f690fc2b890d1370de95ff4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "ConstantEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `ConstantEntity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ConstantDao.class, ConstantDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public ConstantDao constantDao() {
    if (_constantDao != null) {
      return _constantDao;
    } else {
      synchronized(this) {
        if(_constantDao == null) {
          _constantDao = new ConstantDao_Impl(this);
        }
        return _constantDao;
      }
    }
  }
}
