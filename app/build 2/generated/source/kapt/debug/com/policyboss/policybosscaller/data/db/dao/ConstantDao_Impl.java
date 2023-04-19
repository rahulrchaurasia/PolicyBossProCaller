package com.policyboss.policybosscaller.data.db.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.policyboss.policybosscaller.data.model.DashboardData.ConstantEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ConstantDao_Impl implements ConstantDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ConstantEntity> __insertionAdapterOfConstantEntity;

  public ConstantDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfConstantEntity = new EntityInsertionAdapter<ConstantEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ConstantEntity` (`FBAId`,`AddPospVisible`,`CVUrl`,`ERPID`,`EliteKotakUrl`,`FinboxEnabled`,`FinperksEnabled`,`FourWheelerEnabled`,`FourWheelerUrl`,`FullName`,`GenerateMotorLeadsEnabled`,`HealthDemoUrl`,`HealthPopup`,`InvestmentEnabled`,`InvestmentUrl`,`IsDynamicDashEnabled`,`KotakEliteEnabled`,`LeadDashUrl`,`LoginID`,`ManagName`,`MangEmail`,`MangMobile`,`MyMessagesEnabled`,`MyTransactionsEnabled`,`PBByCrnSearch`,`POSPNo`,`POSP_STATUS`,`PospappformEnabled`,`PospletterEnabled`,`RaiseTickitEnabled`,`RaiseTickitUrl`,`SmsTemplatesEnabled`,`Status`,`SuppEmail`,`SuppMobile`,`TermPopup`,`TermPopupurl`,`TwoWheelerEnabled`,`TwoWheelerUrl`,`addposplimit`,`androidproversion`,`boempuid`,`cobrowserisactive`,`cobrowserlicensecode`,`crnmobileno`,`emplat`,`emplng`,`enableInsuranceBusiness`,`enableenrolasposp`,`enablemyaccountupdate`,`enablencd`,`enablesynccontact`,`fba_campaign_id`,`fba_campaign_name`,`fba_uid`,`finboxurl`,`finmartwhatsappno`,`finperkurl`,`hdfc_code`,`healthurl`,`healthurltemp`,`insurancerepositorylink`,`iosuid`,`iosversion`,`isEmployee`,`loanparentdesignation`,`loanparentemail`,`loanparentid`,`loanparentmobile`,`loanparentname`,`loanparentphoto`,`loanselfdesignation`,`loanselfemail`,`loanselfid`,`loanselfmobile`,`loanselfname`,`loanselfphoto`,`loansenddesignation`,`loansendemail`,`loansendid`,`loansendmobile`,`loansendname`,`loansendphoto`,`marketinghomedesciption`,`marketinghomeenabled`,`marketinghomemaxcount`,`marketinghomepopupid`,`marketinghometitle`,`marketinghometransfertype`,`marketinghomeurl`,`messagesender`,`notificationpopupurl`,`notificationpopupurltype`,`paenable`,`parentid`,`plactive`,`plbanner`,`policyByCRNEnabled`,`pospparentdesignation`,`pospparentemail`,`pospparentid`,`pospparentmobile`,`pospparentname`,`pospparentphoto`,`pospselfdesignation`,`pospselfemail`,`pospselfid`,`pospselfmobile`,`pospselfname`,`pospselfphoto`,`pospsenddesignation`,`pospsendemail`,`pospsendid`,`pospsendmobile`,`pospsendname`,`pospsendphoto`,`serviceurl`,`showmyinsurancebusiness`,`uid`,`ultralakshyaenabled`,`userid`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ConstantEntity value) {
        if (value.getFBAId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getFBAId());
        }
        if (value.getAddPospVisible() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAddPospVisible());
        }
        if (value.getCVUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCVUrl());
        }
        if (value.getERPID() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getERPID());
        }
        if (value.getEliteKotakUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEliteKotakUrl());
        }
        if (value.getFinboxEnabled() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getFinboxEnabled());
        }
        if (value.getFinperksEnabled() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getFinperksEnabled());
        }
        if (value.getFourWheelerEnabled() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getFourWheelerEnabled());
        }
        if (value.getFourWheelerUrl() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getFourWheelerUrl());
        }
        if (value.getFullName() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getFullName());
        }
        if (value.getGenerateMotorLeadsEnabled() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getGenerateMotorLeadsEnabled());
        }
        if (value.getHealthDemoUrl() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getHealthDemoUrl());
        }
        if (value.getHealthPopup() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getHealthPopup());
        }
        if (value.getInvestmentEnabled() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getInvestmentEnabled());
        }
        if (value.getInvestmentUrl() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getInvestmentUrl());
        }
        if (value.getIsDynamicDashEnabled() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getIsDynamicDashEnabled());
        }
        if (value.getKotakEliteEnabled() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getKotakEliteEnabled());
        }
        if (value.getLeadDashUrl() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getLeadDashUrl());
        }
        if (value.getLoginID() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getLoginID());
        }
        if (value.getManagName() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getManagName());
        }
        if (value.getMangEmail() == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.getMangEmail());
        }
        if (value.getMangMobile() == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindString(22, value.getMangMobile());
        }
        if (value.getMyMessagesEnabled() == null) {
          stmt.bindNull(23);
        } else {
          stmt.bindString(23, value.getMyMessagesEnabled());
        }
        if (value.getMyTransactionsEnabled() == null) {
          stmt.bindNull(24);
        } else {
          stmt.bindString(24, value.getMyTransactionsEnabled());
        }
        if (value.getPBByCrnSearch() == null) {
          stmt.bindNull(25);
        } else {
          stmt.bindString(25, value.getPBByCrnSearch());
        }
        if (value.getPOSPNo() == null) {
          stmt.bindNull(26);
        } else {
          stmt.bindString(26, value.getPOSPNo());
        }
        if (value.getPOSP_STATUS() == null) {
          stmt.bindNull(27);
        } else {
          stmt.bindString(27, value.getPOSP_STATUS());
        }
        if (value.getPospappformEnabled() == null) {
          stmt.bindNull(28);
        } else {
          stmt.bindString(28, value.getPospappformEnabled());
        }
        if (value.getPospletterEnabled() == null) {
          stmt.bindNull(29);
        } else {
          stmt.bindString(29, value.getPospletterEnabled());
        }
        if (value.getRaiseTickitEnabled() == null) {
          stmt.bindNull(30);
        } else {
          stmt.bindString(30, value.getRaiseTickitEnabled());
        }
        if (value.getRaiseTickitUrl() == null) {
          stmt.bindNull(31);
        } else {
          stmt.bindString(31, value.getRaiseTickitUrl());
        }
        if (value.getSmsTemplatesEnabled() == null) {
          stmt.bindNull(32);
        } else {
          stmt.bindString(32, value.getSmsTemplatesEnabled());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(33);
        } else {
          stmt.bindString(33, value.getStatus());
        }
        if (value.getSuppEmail() == null) {
          stmt.bindNull(34);
        } else {
          stmt.bindString(34, value.getSuppEmail());
        }
        if (value.getSuppMobile() == null) {
          stmt.bindNull(35);
        } else {
          stmt.bindString(35, value.getSuppMobile());
        }
        if (value.getTermPopup() == null) {
          stmt.bindNull(36);
        } else {
          stmt.bindString(36, value.getTermPopup());
        }
        if (value.getTermPopupurl() == null) {
          stmt.bindNull(37);
        } else {
          stmt.bindString(37, value.getTermPopupurl());
        }
        if (value.getTwoWheelerEnabled() == null) {
          stmt.bindNull(38);
        } else {
          stmt.bindString(38, value.getTwoWheelerEnabled());
        }
        if (value.getTwoWheelerUrl() == null) {
          stmt.bindNull(39);
        } else {
          stmt.bindString(39, value.getTwoWheelerUrl());
        }
        if (value.getAddposplimit() == null) {
          stmt.bindNull(40);
        } else {
          stmt.bindString(40, value.getAddposplimit());
        }
        if (value.getAndroidproversion() == null) {
          stmt.bindNull(41);
        } else {
          stmt.bindString(41, value.getAndroidproversion());
        }
        if (value.getBoempuid() == null) {
          stmt.bindNull(42);
        } else {
          stmt.bindString(42, value.getBoempuid());
        }
        if (value.getCobrowserisactive() == null) {
          stmt.bindNull(43);
        } else {
          stmt.bindString(43, value.getCobrowserisactive());
        }
        if (value.getCobrowserlicensecode() == null) {
          stmt.bindNull(44);
        } else {
          stmt.bindString(44, value.getCobrowserlicensecode());
        }
        if (value.getCrnmobileno() == null) {
          stmt.bindNull(45);
        } else {
          stmt.bindString(45, value.getCrnmobileno());
        }
        if (value.getEmplat() == null) {
          stmt.bindNull(46);
        } else {
          stmt.bindString(46, value.getEmplat());
        }
        if (value.getEmplng() == null) {
          stmt.bindNull(47);
        } else {
          stmt.bindString(47, value.getEmplng());
        }
        if (value.getEnableInsuranceBusiness() == null) {
          stmt.bindNull(48);
        } else {
          stmt.bindString(48, value.getEnableInsuranceBusiness());
        }
        if (value.getEnableenrolasposp() == null) {
          stmt.bindNull(49);
        } else {
          stmt.bindString(49, value.getEnableenrolasposp());
        }
        if (value.getEnablemyaccountupdate() == null) {
          stmt.bindNull(50);
        } else {
          stmt.bindString(50, value.getEnablemyaccountupdate());
        }
        if (value.getEnablencd() == null) {
          stmt.bindNull(51);
        } else {
          stmt.bindString(51, value.getEnablencd());
        }
        if (value.getEnablesynccontact() == null) {
          stmt.bindNull(52);
        } else {
          stmt.bindString(52, value.getEnablesynccontact());
        }
        if (value.getFba_campaign_id() == null) {
          stmt.bindNull(53);
        } else {
          stmt.bindString(53, value.getFba_campaign_id());
        }
        if (value.getFba_campaign_name() == null) {
          stmt.bindNull(54);
        } else {
          stmt.bindString(54, value.getFba_campaign_name());
        }
        if (value.getFba_uid() == null) {
          stmt.bindNull(55);
        } else {
          stmt.bindString(55, value.getFba_uid());
        }
        if (value.getFinboxurl() == null) {
          stmt.bindNull(56);
        } else {
          stmt.bindString(56, value.getFinboxurl());
        }
        if (value.getFinmartwhatsappno() == null) {
          stmt.bindNull(57);
        } else {
          stmt.bindString(57, value.getFinmartwhatsappno());
        }
        if (value.getFinperkurl() == null) {
          stmt.bindNull(58);
        } else {
          stmt.bindString(58, value.getFinperkurl());
        }
        if (value.getHdfc_code() == null) {
          stmt.bindNull(59);
        } else {
          stmt.bindString(59, value.getHdfc_code());
        }
        if (value.getHealthurl() == null) {
          stmt.bindNull(60);
        } else {
          stmt.bindString(60, value.getHealthurl());
        }
        if (value.getHealthurltemp() == null) {
          stmt.bindNull(61);
        } else {
          stmt.bindString(61, value.getHealthurltemp());
        }
        if (value.getInsurancerepositorylink() == null) {
          stmt.bindNull(62);
        } else {
          stmt.bindString(62, value.getInsurancerepositorylink());
        }
        if (value.getIosuid() == null) {
          stmt.bindNull(63);
        } else {
          stmt.bindString(63, value.getIosuid());
        }
        if (value.getIosversion() == null) {
          stmt.bindNull(64);
        } else {
          stmt.bindString(64, value.getIosversion());
        }
        if (value.isEmployee() == null) {
          stmt.bindNull(65);
        } else {
          stmt.bindString(65, value.isEmployee());
        }
        if (value.getLoanparentdesignation() == null) {
          stmt.bindNull(66);
        } else {
          stmt.bindString(66, value.getLoanparentdesignation());
        }
        if (value.getLoanparentemail() == null) {
          stmt.bindNull(67);
        } else {
          stmt.bindString(67, value.getLoanparentemail());
        }
        if (value.getLoanparentid() == null) {
          stmt.bindNull(68);
        } else {
          stmt.bindString(68, value.getLoanparentid());
        }
        if (value.getLoanparentmobile() == null) {
          stmt.bindNull(69);
        } else {
          stmt.bindString(69, value.getLoanparentmobile());
        }
        if (value.getLoanparentname() == null) {
          stmt.bindNull(70);
        } else {
          stmt.bindString(70, value.getLoanparentname());
        }
        if (value.getLoanparentphoto() == null) {
          stmt.bindNull(71);
        } else {
          stmt.bindString(71, value.getLoanparentphoto());
        }
        if (value.getLoanselfdesignation() == null) {
          stmt.bindNull(72);
        } else {
          stmt.bindString(72, value.getLoanselfdesignation());
        }
        if (value.getLoanselfemail() == null) {
          stmt.bindNull(73);
        } else {
          stmt.bindString(73, value.getLoanselfemail());
        }
        if (value.getLoanselfid() == null) {
          stmt.bindNull(74);
        } else {
          stmt.bindString(74, value.getLoanselfid());
        }
        if (value.getLoanselfmobile() == null) {
          stmt.bindNull(75);
        } else {
          stmt.bindString(75, value.getLoanselfmobile());
        }
        if (value.getLoanselfname() == null) {
          stmt.bindNull(76);
        } else {
          stmt.bindString(76, value.getLoanselfname());
        }
        if (value.getLoanselfphoto() == null) {
          stmt.bindNull(77);
        } else {
          stmt.bindString(77, value.getLoanselfphoto());
        }
        if (value.getLoansenddesignation() == null) {
          stmt.bindNull(78);
        } else {
          stmt.bindString(78, value.getLoansenddesignation());
        }
        if (value.getLoansendemail() == null) {
          stmt.bindNull(79);
        } else {
          stmt.bindString(79, value.getLoansendemail());
        }
        if (value.getLoansendid() == null) {
          stmt.bindNull(80);
        } else {
          stmt.bindString(80, value.getLoansendid());
        }
        if (value.getLoansendmobile() == null) {
          stmt.bindNull(81);
        } else {
          stmt.bindString(81, value.getLoansendmobile());
        }
        if (value.getLoansendname() == null) {
          stmt.bindNull(82);
        } else {
          stmt.bindString(82, value.getLoansendname());
        }
        if (value.getLoansendphoto() == null) {
          stmt.bindNull(83);
        } else {
          stmt.bindString(83, value.getLoansendphoto());
        }
        if (value.getMarketinghomedesciption() == null) {
          stmt.bindNull(84);
        } else {
          stmt.bindString(84, value.getMarketinghomedesciption());
        }
        if (value.getMarketinghomeenabled() == null) {
          stmt.bindNull(85);
        } else {
          stmt.bindString(85, value.getMarketinghomeenabled());
        }
        if (value.getMarketinghomemaxcount() == null) {
          stmt.bindNull(86);
        } else {
          stmt.bindString(86, value.getMarketinghomemaxcount());
        }
        if (value.getMarketinghomepopupid() == null) {
          stmt.bindNull(87);
        } else {
          stmt.bindString(87, value.getMarketinghomepopupid());
        }
        if (value.getMarketinghometitle() == null) {
          stmt.bindNull(88);
        } else {
          stmt.bindString(88, value.getMarketinghometitle());
        }
        if (value.getMarketinghometransfertype() == null) {
          stmt.bindNull(89);
        } else {
          stmt.bindString(89, value.getMarketinghometransfertype());
        }
        if (value.getMarketinghomeurl() == null) {
          stmt.bindNull(90);
        } else {
          stmt.bindString(90, value.getMarketinghomeurl());
        }
        if (value.getMessagesender() == null) {
          stmt.bindNull(91);
        } else {
          stmt.bindString(91, value.getMessagesender());
        }
        if (value.getNotificationpopupurl() == null) {
          stmt.bindNull(92);
        } else {
          stmt.bindString(92, value.getNotificationpopupurl());
        }
        if (value.getNotificationpopupurltype() == null) {
          stmt.bindNull(93);
        } else {
          stmt.bindString(93, value.getNotificationpopupurltype());
        }
        if (value.getPaenable() == null) {
          stmt.bindNull(94);
        } else {
          stmt.bindString(94, value.getPaenable());
        }
        if (value.getParentid() == null) {
          stmt.bindNull(95);
        } else {
          stmt.bindString(95, value.getParentid());
        }
        if (value.getPlactive() == null) {
          stmt.bindNull(96);
        } else {
          stmt.bindString(96, value.getPlactive());
        }
        if (value.getPlbanner() == null) {
          stmt.bindNull(97);
        } else {
          stmt.bindString(97, value.getPlbanner());
        }
        if (value.getPolicyByCRNEnabled() == null) {
          stmt.bindNull(98);
        } else {
          stmt.bindString(98, value.getPolicyByCRNEnabled());
        }
        if (value.getPospparentdesignation() == null) {
          stmt.bindNull(99);
        } else {
          stmt.bindString(99, value.getPospparentdesignation());
        }
        if (value.getPospparentemail() == null) {
          stmt.bindNull(100);
        } else {
          stmt.bindString(100, value.getPospparentemail());
        }
        if (value.getPospparentid() == null) {
          stmt.bindNull(101);
        } else {
          stmt.bindString(101, value.getPospparentid());
        }
        if (value.getPospparentmobile() == null) {
          stmt.bindNull(102);
        } else {
          stmt.bindString(102, value.getPospparentmobile());
        }
        if (value.getPospparentname() == null) {
          stmt.bindNull(103);
        } else {
          stmt.bindString(103, value.getPospparentname());
        }
        if (value.getPospparentphoto() == null) {
          stmt.bindNull(104);
        } else {
          stmt.bindString(104, value.getPospparentphoto());
        }
        if (value.getPospselfdesignation() == null) {
          stmt.bindNull(105);
        } else {
          stmt.bindString(105, value.getPospselfdesignation());
        }
        if (value.getPospselfemail() == null) {
          stmt.bindNull(106);
        } else {
          stmt.bindString(106, value.getPospselfemail());
        }
        if (value.getPospselfid() == null) {
          stmt.bindNull(107);
        } else {
          stmt.bindString(107, value.getPospselfid());
        }
        if (value.getPospselfmobile() == null) {
          stmt.bindNull(108);
        } else {
          stmt.bindString(108, value.getPospselfmobile());
        }
        if (value.getPospselfname() == null) {
          stmt.bindNull(109);
        } else {
          stmt.bindString(109, value.getPospselfname());
        }
        if (value.getPospselfphoto() == null) {
          stmt.bindNull(110);
        } else {
          stmt.bindString(110, value.getPospselfphoto());
        }
        if (value.getPospsenddesignation() == null) {
          stmt.bindNull(111);
        } else {
          stmt.bindString(111, value.getPospsenddesignation());
        }
        if (value.getPospsendemail() == null) {
          stmt.bindNull(112);
        } else {
          stmt.bindString(112, value.getPospsendemail());
        }
        if (value.getPospsendid() == null) {
          stmt.bindNull(113);
        } else {
          stmt.bindString(113, value.getPospsendid());
        }
        if (value.getPospsendmobile() == null) {
          stmt.bindNull(114);
        } else {
          stmt.bindString(114, value.getPospsendmobile());
        }
        if (value.getPospsendname() == null) {
          stmt.bindNull(115);
        } else {
          stmt.bindString(115, value.getPospsendname());
        }
        if (value.getPospsendphoto() == null) {
          stmt.bindNull(116);
        } else {
          stmt.bindString(116, value.getPospsendphoto());
        }
        if (value.getServiceurl() == null) {
          stmt.bindNull(117);
        } else {
          stmt.bindString(117, value.getServiceurl());
        }
        if (value.getShowmyinsurancebusiness() == null) {
          stmt.bindNull(118);
        } else {
          stmt.bindString(118, value.getShowmyinsurancebusiness());
        }
        if (value.getUid() == null) {
          stmt.bindNull(119);
        } else {
          stmt.bindString(119, value.getUid());
        }
        stmt.bindLong(120, value.getUltralakshyaenabled());
        if (value.getUserid() == null) {
          stmt.bindNull(121);
        } else {
          stmt.bindString(121, value.getUserid());
        }
      }
    };
  }

  @Override
  public Object saveConstantData(final ConstantEntity entitiy,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfConstantEntity.insert(entitiy);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public List<ConstantEntity> getConstantData() {
    final String _sql = "select * from ConstantEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfFBAId = CursorUtil.getColumnIndexOrThrow(_cursor, "FBAId");
      final int _cursorIndexOfAddPospVisible = CursorUtil.getColumnIndexOrThrow(_cursor, "AddPospVisible");
      final int _cursorIndexOfCVUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "CVUrl");
      final int _cursorIndexOfERPID = CursorUtil.getColumnIndexOrThrow(_cursor, "ERPID");
      final int _cursorIndexOfEliteKotakUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "EliteKotakUrl");
      final int _cursorIndexOfFinboxEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "FinboxEnabled");
      final int _cursorIndexOfFinperksEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "FinperksEnabled");
      final int _cursorIndexOfFourWheelerEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "FourWheelerEnabled");
      final int _cursorIndexOfFourWheelerUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "FourWheelerUrl");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfGenerateMotorLeadsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "GenerateMotorLeadsEnabled");
      final int _cursorIndexOfHealthDemoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "HealthDemoUrl");
      final int _cursorIndexOfHealthPopup = CursorUtil.getColumnIndexOrThrow(_cursor, "HealthPopup");
      final int _cursorIndexOfInvestmentEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "InvestmentEnabled");
      final int _cursorIndexOfInvestmentUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "InvestmentUrl");
      final int _cursorIndexOfIsDynamicDashEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "IsDynamicDashEnabled");
      final int _cursorIndexOfKotakEliteEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "KotakEliteEnabled");
      final int _cursorIndexOfLeadDashUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "LeadDashUrl");
      final int _cursorIndexOfLoginID = CursorUtil.getColumnIndexOrThrow(_cursor, "LoginID");
      final int _cursorIndexOfManagName = CursorUtil.getColumnIndexOrThrow(_cursor, "ManagName");
      final int _cursorIndexOfMangEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "MangEmail");
      final int _cursorIndexOfMangMobile = CursorUtil.getColumnIndexOrThrow(_cursor, "MangMobile");
      final int _cursorIndexOfMyMessagesEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "MyMessagesEnabled");
      final int _cursorIndexOfMyTransactionsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "MyTransactionsEnabled");
      final int _cursorIndexOfPBByCrnSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "PBByCrnSearch");
      final int _cursorIndexOfPOSPNo = CursorUtil.getColumnIndexOrThrow(_cursor, "POSPNo");
      final int _cursorIndexOfPOSPSTATUS = CursorUtil.getColumnIndexOrThrow(_cursor, "POSP_STATUS");
      final int _cursorIndexOfPospappformEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "PospappformEnabled");
      final int _cursorIndexOfPospletterEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "PospletterEnabled");
      final int _cursorIndexOfRaiseTickitEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "RaiseTickitEnabled");
      final int _cursorIndexOfRaiseTickitUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "RaiseTickitUrl");
      final int _cursorIndexOfSmsTemplatesEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "SmsTemplatesEnabled");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "Status");
      final int _cursorIndexOfSuppEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "SuppEmail");
      final int _cursorIndexOfSuppMobile = CursorUtil.getColumnIndexOrThrow(_cursor, "SuppMobile");
      final int _cursorIndexOfTermPopup = CursorUtil.getColumnIndexOrThrow(_cursor, "TermPopup");
      final int _cursorIndexOfTermPopupurl = CursorUtil.getColumnIndexOrThrow(_cursor, "TermPopupurl");
      final int _cursorIndexOfTwoWheelerEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "TwoWheelerEnabled");
      final int _cursorIndexOfTwoWheelerUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "TwoWheelerUrl");
      final int _cursorIndexOfAddposplimit = CursorUtil.getColumnIndexOrThrow(_cursor, "addposplimit");
      final int _cursorIndexOfAndroidproversion = CursorUtil.getColumnIndexOrThrow(_cursor, "androidproversion");
      final int _cursorIndexOfBoempuid = CursorUtil.getColumnIndexOrThrow(_cursor, "boempuid");
      final int _cursorIndexOfCobrowserisactive = CursorUtil.getColumnIndexOrThrow(_cursor, "cobrowserisactive");
      final int _cursorIndexOfCobrowserlicensecode = CursorUtil.getColumnIndexOrThrow(_cursor, "cobrowserlicensecode");
      final int _cursorIndexOfCrnmobileno = CursorUtil.getColumnIndexOrThrow(_cursor, "crnmobileno");
      final int _cursorIndexOfEmplat = CursorUtil.getColumnIndexOrThrow(_cursor, "emplat");
      final int _cursorIndexOfEmplng = CursorUtil.getColumnIndexOrThrow(_cursor, "emplng");
      final int _cursorIndexOfEnableInsuranceBusiness = CursorUtil.getColumnIndexOrThrow(_cursor, "enableInsuranceBusiness");
      final int _cursorIndexOfEnableenrolasposp = CursorUtil.getColumnIndexOrThrow(_cursor, "enableenrolasposp");
      final int _cursorIndexOfEnablemyaccountupdate = CursorUtil.getColumnIndexOrThrow(_cursor, "enablemyaccountupdate");
      final int _cursorIndexOfEnablencd = CursorUtil.getColumnIndexOrThrow(_cursor, "enablencd");
      final int _cursorIndexOfEnablesynccontact = CursorUtil.getColumnIndexOrThrow(_cursor, "enablesynccontact");
      final int _cursorIndexOfFbaCampaignId = CursorUtil.getColumnIndexOrThrow(_cursor, "fba_campaign_id");
      final int _cursorIndexOfFbaCampaignName = CursorUtil.getColumnIndexOrThrow(_cursor, "fba_campaign_name");
      final int _cursorIndexOfFbaUid = CursorUtil.getColumnIndexOrThrow(_cursor, "fba_uid");
      final int _cursorIndexOfFinboxurl = CursorUtil.getColumnIndexOrThrow(_cursor, "finboxurl");
      final int _cursorIndexOfFinmartwhatsappno = CursorUtil.getColumnIndexOrThrow(_cursor, "finmartwhatsappno");
      final int _cursorIndexOfFinperkurl = CursorUtil.getColumnIndexOrThrow(_cursor, "finperkurl");
      final int _cursorIndexOfHdfcCode = CursorUtil.getColumnIndexOrThrow(_cursor, "hdfc_code");
      final int _cursorIndexOfHealthurl = CursorUtil.getColumnIndexOrThrow(_cursor, "healthurl");
      final int _cursorIndexOfHealthurltemp = CursorUtil.getColumnIndexOrThrow(_cursor, "healthurltemp");
      final int _cursorIndexOfInsurancerepositorylink = CursorUtil.getColumnIndexOrThrow(_cursor, "insurancerepositorylink");
      final int _cursorIndexOfIosuid = CursorUtil.getColumnIndexOrThrow(_cursor, "iosuid");
      final int _cursorIndexOfIosversion = CursorUtil.getColumnIndexOrThrow(_cursor, "iosversion");
      final int _cursorIndexOfIsEmployee = CursorUtil.getColumnIndexOrThrow(_cursor, "isEmployee");
      final int _cursorIndexOfLoanparentdesignation = CursorUtil.getColumnIndexOrThrow(_cursor, "loanparentdesignation");
      final int _cursorIndexOfLoanparentemail = CursorUtil.getColumnIndexOrThrow(_cursor, "loanparentemail");
      final int _cursorIndexOfLoanparentid = CursorUtil.getColumnIndexOrThrow(_cursor, "loanparentid");
      final int _cursorIndexOfLoanparentmobile = CursorUtil.getColumnIndexOrThrow(_cursor, "loanparentmobile");
      final int _cursorIndexOfLoanparentname = CursorUtil.getColumnIndexOrThrow(_cursor, "loanparentname");
      final int _cursorIndexOfLoanparentphoto = CursorUtil.getColumnIndexOrThrow(_cursor, "loanparentphoto");
      final int _cursorIndexOfLoanselfdesignation = CursorUtil.getColumnIndexOrThrow(_cursor, "loanselfdesignation");
      final int _cursorIndexOfLoanselfemail = CursorUtil.getColumnIndexOrThrow(_cursor, "loanselfemail");
      final int _cursorIndexOfLoanselfid = CursorUtil.getColumnIndexOrThrow(_cursor, "loanselfid");
      final int _cursorIndexOfLoanselfmobile = CursorUtil.getColumnIndexOrThrow(_cursor, "loanselfmobile");
      final int _cursorIndexOfLoanselfname = CursorUtil.getColumnIndexOrThrow(_cursor, "loanselfname");
      final int _cursorIndexOfLoanselfphoto = CursorUtil.getColumnIndexOrThrow(_cursor, "loanselfphoto");
      final int _cursorIndexOfLoansenddesignation = CursorUtil.getColumnIndexOrThrow(_cursor, "loansenddesignation");
      final int _cursorIndexOfLoansendemail = CursorUtil.getColumnIndexOrThrow(_cursor, "loansendemail");
      final int _cursorIndexOfLoansendid = CursorUtil.getColumnIndexOrThrow(_cursor, "loansendid");
      final int _cursorIndexOfLoansendmobile = CursorUtil.getColumnIndexOrThrow(_cursor, "loansendmobile");
      final int _cursorIndexOfLoansendname = CursorUtil.getColumnIndexOrThrow(_cursor, "loansendname");
      final int _cursorIndexOfLoansendphoto = CursorUtil.getColumnIndexOrThrow(_cursor, "loansendphoto");
      final int _cursorIndexOfMarketinghomedesciption = CursorUtil.getColumnIndexOrThrow(_cursor, "marketinghomedesciption");
      final int _cursorIndexOfMarketinghomeenabled = CursorUtil.getColumnIndexOrThrow(_cursor, "marketinghomeenabled");
      final int _cursorIndexOfMarketinghomemaxcount = CursorUtil.getColumnIndexOrThrow(_cursor, "marketinghomemaxcount");
      final int _cursorIndexOfMarketinghomepopupid = CursorUtil.getColumnIndexOrThrow(_cursor, "marketinghomepopupid");
      final int _cursorIndexOfMarketinghometitle = CursorUtil.getColumnIndexOrThrow(_cursor, "marketinghometitle");
      final int _cursorIndexOfMarketinghometransfertype = CursorUtil.getColumnIndexOrThrow(_cursor, "marketinghometransfertype");
      final int _cursorIndexOfMarketinghomeurl = CursorUtil.getColumnIndexOrThrow(_cursor, "marketinghomeurl");
      final int _cursorIndexOfMessagesender = CursorUtil.getColumnIndexOrThrow(_cursor, "messagesender");
      final int _cursorIndexOfNotificationpopupurl = CursorUtil.getColumnIndexOrThrow(_cursor, "notificationpopupurl");
      final int _cursorIndexOfNotificationpopupurltype = CursorUtil.getColumnIndexOrThrow(_cursor, "notificationpopupurltype");
      final int _cursorIndexOfPaenable = CursorUtil.getColumnIndexOrThrow(_cursor, "paenable");
      final int _cursorIndexOfParentid = CursorUtil.getColumnIndexOrThrow(_cursor, "parentid");
      final int _cursorIndexOfPlactive = CursorUtil.getColumnIndexOrThrow(_cursor, "plactive");
      final int _cursorIndexOfPlbanner = CursorUtil.getColumnIndexOrThrow(_cursor, "plbanner");
      final int _cursorIndexOfPolicyByCRNEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "policyByCRNEnabled");
      final int _cursorIndexOfPospparentdesignation = CursorUtil.getColumnIndexOrThrow(_cursor, "pospparentdesignation");
      final int _cursorIndexOfPospparentemail = CursorUtil.getColumnIndexOrThrow(_cursor, "pospparentemail");
      final int _cursorIndexOfPospparentid = CursorUtil.getColumnIndexOrThrow(_cursor, "pospparentid");
      final int _cursorIndexOfPospparentmobile = CursorUtil.getColumnIndexOrThrow(_cursor, "pospparentmobile");
      final int _cursorIndexOfPospparentname = CursorUtil.getColumnIndexOrThrow(_cursor, "pospparentname");
      final int _cursorIndexOfPospparentphoto = CursorUtil.getColumnIndexOrThrow(_cursor, "pospparentphoto");
      final int _cursorIndexOfPospselfdesignation = CursorUtil.getColumnIndexOrThrow(_cursor, "pospselfdesignation");
      final int _cursorIndexOfPospselfemail = CursorUtil.getColumnIndexOrThrow(_cursor, "pospselfemail");
      final int _cursorIndexOfPospselfid = CursorUtil.getColumnIndexOrThrow(_cursor, "pospselfid");
      final int _cursorIndexOfPospselfmobile = CursorUtil.getColumnIndexOrThrow(_cursor, "pospselfmobile");
      final int _cursorIndexOfPospselfname = CursorUtil.getColumnIndexOrThrow(_cursor, "pospselfname");
      final int _cursorIndexOfPospselfphoto = CursorUtil.getColumnIndexOrThrow(_cursor, "pospselfphoto");
      final int _cursorIndexOfPospsenddesignation = CursorUtil.getColumnIndexOrThrow(_cursor, "pospsenddesignation");
      final int _cursorIndexOfPospsendemail = CursorUtil.getColumnIndexOrThrow(_cursor, "pospsendemail");
      final int _cursorIndexOfPospsendid = CursorUtil.getColumnIndexOrThrow(_cursor, "pospsendid");
      final int _cursorIndexOfPospsendmobile = CursorUtil.getColumnIndexOrThrow(_cursor, "pospsendmobile");
      final int _cursorIndexOfPospsendname = CursorUtil.getColumnIndexOrThrow(_cursor, "pospsendname");
      final int _cursorIndexOfPospsendphoto = CursorUtil.getColumnIndexOrThrow(_cursor, "pospsendphoto");
      final int _cursorIndexOfServiceurl = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceurl");
      final int _cursorIndexOfShowmyinsurancebusiness = CursorUtil.getColumnIndexOrThrow(_cursor, "showmyinsurancebusiness");
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfUltralakshyaenabled = CursorUtil.getColumnIndexOrThrow(_cursor, "ultralakshyaenabled");
      final int _cursorIndexOfUserid = CursorUtil.getColumnIndexOrThrow(_cursor, "userid");
      final List<ConstantEntity> _result = new ArrayList<ConstantEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ConstantEntity _item;
        final String _tmpFBAId;
        if (_cursor.isNull(_cursorIndexOfFBAId)) {
          _tmpFBAId = null;
        } else {
          _tmpFBAId = _cursor.getString(_cursorIndexOfFBAId);
        }
        final String _tmpAddPospVisible;
        if (_cursor.isNull(_cursorIndexOfAddPospVisible)) {
          _tmpAddPospVisible = null;
        } else {
          _tmpAddPospVisible = _cursor.getString(_cursorIndexOfAddPospVisible);
        }
        final String _tmpCVUrl;
        if (_cursor.isNull(_cursorIndexOfCVUrl)) {
          _tmpCVUrl = null;
        } else {
          _tmpCVUrl = _cursor.getString(_cursorIndexOfCVUrl);
        }
        final String _tmpERPID;
        if (_cursor.isNull(_cursorIndexOfERPID)) {
          _tmpERPID = null;
        } else {
          _tmpERPID = _cursor.getString(_cursorIndexOfERPID);
        }
        final String _tmpEliteKotakUrl;
        if (_cursor.isNull(_cursorIndexOfEliteKotakUrl)) {
          _tmpEliteKotakUrl = null;
        } else {
          _tmpEliteKotakUrl = _cursor.getString(_cursorIndexOfEliteKotakUrl);
        }
        final String _tmpFinboxEnabled;
        if (_cursor.isNull(_cursorIndexOfFinboxEnabled)) {
          _tmpFinboxEnabled = null;
        } else {
          _tmpFinboxEnabled = _cursor.getString(_cursorIndexOfFinboxEnabled);
        }
        final String _tmpFinperksEnabled;
        if (_cursor.isNull(_cursorIndexOfFinperksEnabled)) {
          _tmpFinperksEnabled = null;
        } else {
          _tmpFinperksEnabled = _cursor.getString(_cursorIndexOfFinperksEnabled);
        }
        final String _tmpFourWheelerEnabled;
        if (_cursor.isNull(_cursorIndexOfFourWheelerEnabled)) {
          _tmpFourWheelerEnabled = null;
        } else {
          _tmpFourWheelerEnabled = _cursor.getString(_cursorIndexOfFourWheelerEnabled);
        }
        final String _tmpFourWheelerUrl;
        if (_cursor.isNull(_cursorIndexOfFourWheelerUrl)) {
          _tmpFourWheelerUrl = null;
        } else {
          _tmpFourWheelerUrl = _cursor.getString(_cursorIndexOfFourWheelerUrl);
        }
        final String _tmpFullName;
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _tmpFullName = null;
        } else {
          _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        }
        final String _tmpGenerateMotorLeadsEnabled;
        if (_cursor.isNull(_cursorIndexOfGenerateMotorLeadsEnabled)) {
          _tmpGenerateMotorLeadsEnabled = null;
        } else {
          _tmpGenerateMotorLeadsEnabled = _cursor.getString(_cursorIndexOfGenerateMotorLeadsEnabled);
        }
        final String _tmpHealthDemoUrl;
        if (_cursor.isNull(_cursorIndexOfHealthDemoUrl)) {
          _tmpHealthDemoUrl = null;
        } else {
          _tmpHealthDemoUrl = _cursor.getString(_cursorIndexOfHealthDemoUrl);
        }
        final String _tmpHealthPopup;
        if (_cursor.isNull(_cursorIndexOfHealthPopup)) {
          _tmpHealthPopup = null;
        } else {
          _tmpHealthPopup = _cursor.getString(_cursorIndexOfHealthPopup);
        }
        final String _tmpInvestmentEnabled;
        if (_cursor.isNull(_cursorIndexOfInvestmentEnabled)) {
          _tmpInvestmentEnabled = null;
        } else {
          _tmpInvestmentEnabled = _cursor.getString(_cursorIndexOfInvestmentEnabled);
        }
        final String _tmpInvestmentUrl;
        if (_cursor.isNull(_cursorIndexOfInvestmentUrl)) {
          _tmpInvestmentUrl = null;
        } else {
          _tmpInvestmentUrl = _cursor.getString(_cursorIndexOfInvestmentUrl);
        }
        final String _tmpIsDynamicDashEnabled;
        if (_cursor.isNull(_cursorIndexOfIsDynamicDashEnabled)) {
          _tmpIsDynamicDashEnabled = null;
        } else {
          _tmpIsDynamicDashEnabled = _cursor.getString(_cursorIndexOfIsDynamicDashEnabled);
        }
        final String _tmpKotakEliteEnabled;
        if (_cursor.isNull(_cursorIndexOfKotakEliteEnabled)) {
          _tmpKotakEliteEnabled = null;
        } else {
          _tmpKotakEliteEnabled = _cursor.getString(_cursorIndexOfKotakEliteEnabled);
        }
        final String _tmpLeadDashUrl;
        if (_cursor.isNull(_cursorIndexOfLeadDashUrl)) {
          _tmpLeadDashUrl = null;
        } else {
          _tmpLeadDashUrl = _cursor.getString(_cursorIndexOfLeadDashUrl);
        }
        final String _tmpLoginID;
        if (_cursor.isNull(_cursorIndexOfLoginID)) {
          _tmpLoginID = null;
        } else {
          _tmpLoginID = _cursor.getString(_cursorIndexOfLoginID);
        }
        final String _tmpManagName;
        if (_cursor.isNull(_cursorIndexOfManagName)) {
          _tmpManagName = null;
        } else {
          _tmpManagName = _cursor.getString(_cursorIndexOfManagName);
        }
        final String _tmpMangEmail;
        if (_cursor.isNull(_cursorIndexOfMangEmail)) {
          _tmpMangEmail = null;
        } else {
          _tmpMangEmail = _cursor.getString(_cursorIndexOfMangEmail);
        }
        final String _tmpMangMobile;
        if (_cursor.isNull(_cursorIndexOfMangMobile)) {
          _tmpMangMobile = null;
        } else {
          _tmpMangMobile = _cursor.getString(_cursorIndexOfMangMobile);
        }
        final String _tmpMyMessagesEnabled;
        if (_cursor.isNull(_cursorIndexOfMyMessagesEnabled)) {
          _tmpMyMessagesEnabled = null;
        } else {
          _tmpMyMessagesEnabled = _cursor.getString(_cursorIndexOfMyMessagesEnabled);
        }
        final String _tmpMyTransactionsEnabled;
        if (_cursor.isNull(_cursorIndexOfMyTransactionsEnabled)) {
          _tmpMyTransactionsEnabled = null;
        } else {
          _tmpMyTransactionsEnabled = _cursor.getString(_cursorIndexOfMyTransactionsEnabled);
        }
        final String _tmpPBByCrnSearch;
        if (_cursor.isNull(_cursorIndexOfPBByCrnSearch)) {
          _tmpPBByCrnSearch = null;
        } else {
          _tmpPBByCrnSearch = _cursor.getString(_cursorIndexOfPBByCrnSearch);
        }
        final String _tmpPOSPNo;
        if (_cursor.isNull(_cursorIndexOfPOSPNo)) {
          _tmpPOSPNo = null;
        } else {
          _tmpPOSPNo = _cursor.getString(_cursorIndexOfPOSPNo);
        }
        final String _tmpPOSP_STATUS;
        if (_cursor.isNull(_cursorIndexOfPOSPSTATUS)) {
          _tmpPOSP_STATUS = null;
        } else {
          _tmpPOSP_STATUS = _cursor.getString(_cursorIndexOfPOSPSTATUS);
        }
        final String _tmpPospappformEnabled;
        if (_cursor.isNull(_cursorIndexOfPospappformEnabled)) {
          _tmpPospappformEnabled = null;
        } else {
          _tmpPospappformEnabled = _cursor.getString(_cursorIndexOfPospappformEnabled);
        }
        final String _tmpPospletterEnabled;
        if (_cursor.isNull(_cursorIndexOfPospletterEnabled)) {
          _tmpPospletterEnabled = null;
        } else {
          _tmpPospletterEnabled = _cursor.getString(_cursorIndexOfPospletterEnabled);
        }
        final String _tmpRaiseTickitEnabled;
        if (_cursor.isNull(_cursorIndexOfRaiseTickitEnabled)) {
          _tmpRaiseTickitEnabled = null;
        } else {
          _tmpRaiseTickitEnabled = _cursor.getString(_cursorIndexOfRaiseTickitEnabled);
        }
        final String _tmpRaiseTickitUrl;
        if (_cursor.isNull(_cursorIndexOfRaiseTickitUrl)) {
          _tmpRaiseTickitUrl = null;
        } else {
          _tmpRaiseTickitUrl = _cursor.getString(_cursorIndexOfRaiseTickitUrl);
        }
        final String _tmpSmsTemplatesEnabled;
        if (_cursor.isNull(_cursorIndexOfSmsTemplatesEnabled)) {
          _tmpSmsTemplatesEnabled = null;
        } else {
          _tmpSmsTemplatesEnabled = _cursor.getString(_cursorIndexOfSmsTemplatesEnabled);
        }
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        final String _tmpSuppEmail;
        if (_cursor.isNull(_cursorIndexOfSuppEmail)) {
          _tmpSuppEmail = null;
        } else {
          _tmpSuppEmail = _cursor.getString(_cursorIndexOfSuppEmail);
        }
        final String _tmpSuppMobile;
        if (_cursor.isNull(_cursorIndexOfSuppMobile)) {
          _tmpSuppMobile = null;
        } else {
          _tmpSuppMobile = _cursor.getString(_cursorIndexOfSuppMobile);
        }
        final String _tmpTermPopup;
        if (_cursor.isNull(_cursorIndexOfTermPopup)) {
          _tmpTermPopup = null;
        } else {
          _tmpTermPopup = _cursor.getString(_cursorIndexOfTermPopup);
        }
        final String _tmpTermPopupurl;
        if (_cursor.isNull(_cursorIndexOfTermPopupurl)) {
          _tmpTermPopupurl = null;
        } else {
          _tmpTermPopupurl = _cursor.getString(_cursorIndexOfTermPopupurl);
        }
        final String _tmpTwoWheelerEnabled;
        if (_cursor.isNull(_cursorIndexOfTwoWheelerEnabled)) {
          _tmpTwoWheelerEnabled = null;
        } else {
          _tmpTwoWheelerEnabled = _cursor.getString(_cursorIndexOfTwoWheelerEnabled);
        }
        final String _tmpTwoWheelerUrl;
        if (_cursor.isNull(_cursorIndexOfTwoWheelerUrl)) {
          _tmpTwoWheelerUrl = null;
        } else {
          _tmpTwoWheelerUrl = _cursor.getString(_cursorIndexOfTwoWheelerUrl);
        }
        final String _tmpAddposplimit;
        if (_cursor.isNull(_cursorIndexOfAddposplimit)) {
          _tmpAddposplimit = null;
        } else {
          _tmpAddposplimit = _cursor.getString(_cursorIndexOfAddposplimit);
        }
        final String _tmpAndroidproversion;
        if (_cursor.isNull(_cursorIndexOfAndroidproversion)) {
          _tmpAndroidproversion = null;
        } else {
          _tmpAndroidproversion = _cursor.getString(_cursorIndexOfAndroidproversion);
        }
        final String _tmpBoempuid;
        if (_cursor.isNull(_cursorIndexOfBoempuid)) {
          _tmpBoempuid = null;
        } else {
          _tmpBoempuid = _cursor.getString(_cursorIndexOfBoempuid);
        }
        final String _tmpCobrowserisactive;
        if (_cursor.isNull(_cursorIndexOfCobrowserisactive)) {
          _tmpCobrowserisactive = null;
        } else {
          _tmpCobrowserisactive = _cursor.getString(_cursorIndexOfCobrowserisactive);
        }
        final String _tmpCobrowserlicensecode;
        if (_cursor.isNull(_cursorIndexOfCobrowserlicensecode)) {
          _tmpCobrowserlicensecode = null;
        } else {
          _tmpCobrowserlicensecode = _cursor.getString(_cursorIndexOfCobrowserlicensecode);
        }
        final String _tmpCrnmobileno;
        if (_cursor.isNull(_cursorIndexOfCrnmobileno)) {
          _tmpCrnmobileno = null;
        } else {
          _tmpCrnmobileno = _cursor.getString(_cursorIndexOfCrnmobileno);
        }
        final String _tmpEmplat;
        if (_cursor.isNull(_cursorIndexOfEmplat)) {
          _tmpEmplat = null;
        } else {
          _tmpEmplat = _cursor.getString(_cursorIndexOfEmplat);
        }
        final String _tmpEmplng;
        if (_cursor.isNull(_cursorIndexOfEmplng)) {
          _tmpEmplng = null;
        } else {
          _tmpEmplng = _cursor.getString(_cursorIndexOfEmplng);
        }
        final String _tmpEnableInsuranceBusiness;
        if (_cursor.isNull(_cursorIndexOfEnableInsuranceBusiness)) {
          _tmpEnableInsuranceBusiness = null;
        } else {
          _tmpEnableInsuranceBusiness = _cursor.getString(_cursorIndexOfEnableInsuranceBusiness);
        }
        final String _tmpEnableenrolasposp;
        if (_cursor.isNull(_cursorIndexOfEnableenrolasposp)) {
          _tmpEnableenrolasposp = null;
        } else {
          _tmpEnableenrolasposp = _cursor.getString(_cursorIndexOfEnableenrolasposp);
        }
        final String _tmpEnablemyaccountupdate;
        if (_cursor.isNull(_cursorIndexOfEnablemyaccountupdate)) {
          _tmpEnablemyaccountupdate = null;
        } else {
          _tmpEnablemyaccountupdate = _cursor.getString(_cursorIndexOfEnablemyaccountupdate);
        }
        final String _tmpEnablencd;
        if (_cursor.isNull(_cursorIndexOfEnablencd)) {
          _tmpEnablencd = null;
        } else {
          _tmpEnablencd = _cursor.getString(_cursorIndexOfEnablencd);
        }
        final String _tmpEnablesynccontact;
        if (_cursor.isNull(_cursorIndexOfEnablesynccontact)) {
          _tmpEnablesynccontact = null;
        } else {
          _tmpEnablesynccontact = _cursor.getString(_cursorIndexOfEnablesynccontact);
        }
        final String _tmpFba_campaign_id;
        if (_cursor.isNull(_cursorIndexOfFbaCampaignId)) {
          _tmpFba_campaign_id = null;
        } else {
          _tmpFba_campaign_id = _cursor.getString(_cursorIndexOfFbaCampaignId);
        }
        final String _tmpFba_campaign_name;
        if (_cursor.isNull(_cursorIndexOfFbaCampaignName)) {
          _tmpFba_campaign_name = null;
        } else {
          _tmpFba_campaign_name = _cursor.getString(_cursorIndexOfFbaCampaignName);
        }
        final String _tmpFba_uid;
        if (_cursor.isNull(_cursorIndexOfFbaUid)) {
          _tmpFba_uid = null;
        } else {
          _tmpFba_uid = _cursor.getString(_cursorIndexOfFbaUid);
        }
        final String _tmpFinboxurl;
        if (_cursor.isNull(_cursorIndexOfFinboxurl)) {
          _tmpFinboxurl = null;
        } else {
          _tmpFinboxurl = _cursor.getString(_cursorIndexOfFinboxurl);
        }
        final String _tmpFinmartwhatsappno;
        if (_cursor.isNull(_cursorIndexOfFinmartwhatsappno)) {
          _tmpFinmartwhatsappno = null;
        } else {
          _tmpFinmartwhatsappno = _cursor.getString(_cursorIndexOfFinmartwhatsappno);
        }
        final String _tmpFinperkurl;
        if (_cursor.isNull(_cursorIndexOfFinperkurl)) {
          _tmpFinperkurl = null;
        } else {
          _tmpFinperkurl = _cursor.getString(_cursorIndexOfFinperkurl);
        }
        final String _tmpHdfc_code;
        if (_cursor.isNull(_cursorIndexOfHdfcCode)) {
          _tmpHdfc_code = null;
        } else {
          _tmpHdfc_code = _cursor.getString(_cursorIndexOfHdfcCode);
        }
        final String _tmpHealthurl;
        if (_cursor.isNull(_cursorIndexOfHealthurl)) {
          _tmpHealthurl = null;
        } else {
          _tmpHealthurl = _cursor.getString(_cursorIndexOfHealthurl);
        }
        final String _tmpHealthurltemp;
        if (_cursor.isNull(_cursorIndexOfHealthurltemp)) {
          _tmpHealthurltemp = null;
        } else {
          _tmpHealthurltemp = _cursor.getString(_cursorIndexOfHealthurltemp);
        }
        final String _tmpInsurancerepositorylink;
        if (_cursor.isNull(_cursorIndexOfInsurancerepositorylink)) {
          _tmpInsurancerepositorylink = null;
        } else {
          _tmpInsurancerepositorylink = _cursor.getString(_cursorIndexOfInsurancerepositorylink);
        }
        final String _tmpIosuid;
        if (_cursor.isNull(_cursorIndexOfIosuid)) {
          _tmpIosuid = null;
        } else {
          _tmpIosuid = _cursor.getString(_cursorIndexOfIosuid);
        }
        final String _tmpIosversion;
        if (_cursor.isNull(_cursorIndexOfIosversion)) {
          _tmpIosversion = null;
        } else {
          _tmpIosversion = _cursor.getString(_cursorIndexOfIosversion);
        }
        final String _tmpIsEmployee;
        if (_cursor.isNull(_cursorIndexOfIsEmployee)) {
          _tmpIsEmployee = null;
        } else {
          _tmpIsEmployee = _cursor.getString(_cursorIndexOfIsEmployee);
        }
        final String _tmpLoanparentdesignation;
        if (_cursor.isNull(_cursorIndexOfLoanparentdesignation)) {
          _tmpLoanparentdesignation = null;
        } else {
          _tmpLoanparentdesignation = _cursor.getString(_cursorIndexOfLoanparentdesignation);
        }
        final String _tmpLoanparentemail;
        if (_cursor.isNull(_cursorIndexOfLoanparentemail)) {
          _tmpLoanparentemail = null;
        } else {
          _tmpLoanparentemail = _cursor.getString(_cursorIndexOfLoanparentemail);
        }
        final String _tmpLoanparentid;
        if (_cursor.isNull(_cursorIndexOfLoanparentid)) {
          _tmpLoanparentid = null;
        } else {
          _tmpLoanparentid = _cursor.getString(_cursorIndexOfLoanparentid);
        }
        final String _tmpLoanparentmobile;
        if (_cursor.isNull(_cursorIndexOfLoanparentmobile)) {
          _tmpLoanparentmobile = null;
        } else {
          _tmpLoanparentmobile = _cursor.getString(_cursorIndexOfLoanparentmobile);
        }
        final String _tmpLoanparentname;
        if (_cursor.isNull(_cursorIndexOfLoanparentname)) {
          _tmpLoanparentname = null;
        } else {
          _tmpLoanparentname = _cursor.getString(_cursorIndexOfLoanparentname);
        }
        final String _tmpLoanparentphoto;
        if (_cursor.isNull(_cursorIndexOfLoanparentphoto)) {
          _tmpLoanparentphoto = null;
        } else {
          _tmpLoanparentphoto = _cursor.getString(_cursorIndexOfLoanparentphoto);
        }
        final String _tmpLoanselfdesignation;
        if (_cursor.isNull(_cursorIndexOfLoanselfdesignation)) {
          _tmpLoanselfdesignation = null;
        } else {
          _tmpLoanselfdesignation = _cursor.getString(_cursorIndexOfLoanselfdesignation);
        }
        final String _tmpLoanselfemail;
        if (_cursor.isNull(_cursorIndexOfLoanselfemail)) {
          _tmpLoanselfemail = null;
        } else {
          _tmpLoanselfemail = _cursor.getString(_cursorIndexOfLoanselfemail);
        }
        final String _tmpLoanselfid;
        if (_cursor.isNull(_cursorIndexOfLoanselfid)) {
          _tmpLoanselfid = null;
        } else {
          _tmpLoanselfid = _cursor.getString(_cursorIndexOfLoanselfid);
        }
        final String _tmpLoanselfmobile;
        if (_cursor.isNull(_cursorIndexOfLoanselfmobile)) {
          _tmpLoanselfmobile = null;
        } else {
          _tmpLoanselfmobile = _cursor.getString(_cursorIndexOfLoanselfmobile);
        }
        final String _tmpLoanselfname;
        if (_cursor.isNull(_cursorIndexOfLoanselfname)) {
          _tmpLoanselfname = null;
        } else {
          _tmpLoanselfname = _cursor.getString(_cursorIndexOfLoanselfname);
        }
        final String _tmpLoanselfphoto;
        if (_cursor.isNull(_cursorIndexOfLoanselfphoto)) {
          _tmpLoanselfphoto = null;
        } else {
          _tmpLoanselfphoto = _cursor.getString(_cursorIndexOfLoanselfphoto);
        }
        final String _tmpLoansenddesignation;
        if (_cursor.isNull(_cursorIndexOfLoansenddesignation)) {
          _tmpLoansenddesignation = null;
        } else {
          _tmpLoansenddesignation = _cursor.getString(_cursorIndexOfLoansenddesignation);
        }
        final String _tmpLoansendemail;
        if (_cursor.isNull(_cursorIndexOfLoansendemail)) {
          _tmpLoansendemail = null;
        } else {
          _tmpLoansendemail = _cursor.getString(_cursorIndexOfLoansendemail);
        }
        final String _tmpLoansendid;
        if (_cursor.isNull(_cursorIndexOfLoansendid)) {
          _tmpLoansendid = null;
        } else {
          _tmpLoansendid = _cursor.getString(_cursorIndexOfLoansendid);
        }
        final String _tmpLoansendmobile;
        if (_cursor.isNull(_cursorIndexOfLoansendmobile)) {
          _tmpLoansendmobile = null;
        } else {
          _tmpLoansendmobile = _cursor.getString(_cursorIndexOfLoansendmobile);
        }
        final String _tmpLoansendname;
        if (_cursor.isNull(_cursorIndexOfLoansendname)) {
          _tmpLoansendname = null;
        } else {
          _tmpLoansendname = _cursor.getString(_cursorIndexOfLoansendname);
        }
        final String _tmpLoansendphoto;
        if (_cursor.isNull(_cursorIndexOfLoansendphoto)) {
          _tmpLoansendphoto = null;
        } else {
          _tmpLoansendphoto = _cursor.getString(_cursorIndexOfLoansendphoto);
        }
        final String _tmpMarketinghomedesciption;
        if (_cursor.isNull(_cursorIndexOfMarketinghomedesciption)) {
          _tmpMarketinghomedesciption = null;
        } else {
          _tmpMarketinghomedesciption = _cursor.getString(_cursorIndexOfMarketinghomedesciption);
        }
        final String _tmpMarketinghomeenabled;
        if (_cursor.isNull(_cursorIndexOfMarketinghomeenabled)) {
          _tmpMarketinghomeenabled = null;
        } else {
          _tmpMarketinghomeenabled = _cursor.getString(_cursorIndexOfMarketinghomeenabled);
        }
        final String _tmpMarketinghomemaxcount;
        if (_cursor.isNull(_cursorIndexOfMarketinghomemaxcount)) {
          _tmpMarketinghomemaxcount = null;
        } else {
          _tmpMarketinghomemaxcount = _cursor.getString(_cursorIndexOfMarketinghomemaxcount);
        }
        final String _tmpMarketinghomepopupid;
        if (_cursor.isNull(_cursorIndexOfMarketinghomepopupid)) {
          _tmpMarketinghomepopupid = null;
        } else {
          _tmpMarketinghomepopupid = _cursor.getString(_cursorIndexOfMarketinghomepopupid);
        }
        final String _tmpMarketinghometitle;
        if (_cursor.isNull(_cursorIndexOfMarketinghometitle)) {
          _tmpMarketinghometitle = null;
        } else {
          _tmpMarketinghometitle = _cursor.getString(_cursorIndexOfMarketinghometitle);
        }
        final String _tmpMarketinghometransfertype;
        if (_cursor.isNull(_cursorIndexOfMarketinghometransfertype)) {
          _tmpMarketinghometransfertype = null;
        } else {
          _tmpMarketinghometransfertype = _cursor.getString(_cursorIndexOfMarketinghometransfertype);
        }
        final String _tmpMarketinghomeurl;
        if (_cursor.isNull(_cursorIndexOfMarketinghomeurl)) {
          _tmpMarketinghomeurl = null;
        } else {
          _tmpMarketinghomeurl = _cursor.getString(_cursorIndexOfMarketinghomeurl);
        }
        final String _tmpMessagesender;
        if (_cursor.isNull(_cursorIndexOfMessagesender)) {
          _tmpMessagesender = null;
        } else {
          _tmpMessagesender = _cursor.getString(_cursorIndexOfMessagesender);
        }
        final String _tmpNotificationpopupurl;
        if (_cursor.isNull(_cursorIndexOfNotificationpopupurl)) {
          _tmpNotificationpopupurl = null;
        } else {
          _tmpNotificationpopupurl = _cursor.getString(_cursorIndexOfNotificationpopupurl);
        }
        final String _tmpNotificationpopupurltype;
        if (_cursor.isNull(_cursorIndexOfNotificationpopupurltype)) {
          _tmpNotificationpopupurltype = null;
        } else {
          _tmpNotificationpopupurltype = _cursor.getString(_cursorIndexOfNotificationpopupurltype);
        }
        final String _tmpPaenable;
        if (_cursor.isNull(_cursorIndexOfPaenable)) {
          _tmpPaenable = null;
        } else {
          _tmpPaenable = _cursor.getString(_cursorIndexOfPaenable);
        }
        final String _tmpParentid;
        if (_cursor.isNull(_cursorIndexOfParentid)) {
          _tmpParentid = null;
        } else {
          _tmpParentid = _cursor.getString(_cursorIndexOfParentid);
        }
        final String _tmpPlactive;
        if (_cursor.isNull(_cursorIndexOfPlactive)) {
          _tmpPlactive = null;
        } else {
          _tmpPlactive = _cursor.getString(_cursorIndexOfPlactive);
        }
        final String _tmpPlbanner;
        if (_cursor.isNull(_cursorIndexOfPlbanner)) {
          _tmpPlbanner = null;
        } else {
          _tmpPlbanner = _cursor.getString(_cursorIndexOfPlbanner);
        }
        final String _tmpPolicyByCRNEnabled;
        if (_cursor.isNull(_cursorIndexOfPolicyByCRNEnabled)) {
          _tmpPolicyByCRNEnabled = null;
        } else {
          _tmpPolicyByCRNEnabled = _cursor.getString(_cursorIndexOfPolicyByCRNEnabled);
        }
        final String _tmpPospparentdesignation;
        if (_cursor.isNull(_cursorIndexOfPospparentdesignation)) {
          _tmpPospparentdesignation = null;
        } else {
          _tmpPospparentdesignation = _cursor.getString(_cursorIndexOfPospparentdesignation);
        }
        final String _tmpPospparentemail;
        if (_cursor.isNull(_cursorIndexOfPospparentemail)) {
          _tmpPospparentemail = null;
        } else {
          _tmpPospparentemail = _cursor.getString(_cursorIndexOfPospparentemail);
        }
        final String _tmpPospparentid;
        if (_cursor.isNull(_cursorIndexOfPospparentid)) {
          _tmpPospparentid = null;
        } else {
          _tmpPospparentid = _cursor.getString(_cursorIndexOfPospparentid);
        }
        final String _tmpPospparentmobile;
        if (_cursor.isNull(_cursorIndexOfPospparentmobile)) {
          _tmpPospparentmobile = null;
        } else {
          _tmpPospparentmobile = _cursor.getString(_cursorIndexOfPospparentmobile);
        }
        final String _tmpPospparentname;
        if (_cursor.isNull(_cursorIndexOfPospparentname)) {
          _tmpPospparentname = null;
        } else {
          _tmpPospparentname = _cursor.getString(_cursorIndexOfPospparentname);
        }
        final String _tmpPospparentphoto;
        if (_cursor.isNull(_cursorIndexOfPospparentphoto)) {
          _tmpPospparentphoto = null;
        } else {
          _tmpPospparentphoto = _cursor.getString(_cursorIndexOfPospparentphoto);
        }
        final String _tmpPospselfdesignation;
        if (_cursor.isNull(_cursorIndexOfPospselfdesignation)) {
          _tmpPospselfdesignation = null;
        } else {
          _tmpPospselfdesignation = _cursor.getString(_cursorIndexOfPospselfdesignation);
        }
        final String _tmpPospselfemail;
        if (_cursor.isNull(_cursorIndexOfPospselfemail)) {
          _tmpPospselfemail = null;
        } else {
          _tmpPospselfemail = _cursor.getString(_cursorIndexOfPospselfemail);
        }
        final String _tmpPospselfid;
        if (_cursor.isNull(_cursorIndexOfPospselfid)) {
          _tmpPospselfid = null;
        } else {
          _tmpPospselfid = _cursor.getString(_cursorIndexOfPospselfid);
        }
        final String _tmpPospselfmobile;
        if (_cursor.isNull(_cursorIndexOfPospselfmobile)) {
          _tmpPospselfmobile = null;
        } else {
          _tmpPospselfmobile = _cursor.getString(_cursorIndexOfPospselfmobile);
        }
        final String _tmpPospselfname;
        if (_cursor.isNull(_cursorIndexOfPospselfname)) {
          _tmpPospselfname = null;
        } else {
          _tmpPospselfname = _cursor.getString(_cursorIndexOfPospselfname);
        }
        final String _tmpPospselfphoto;
        if (_cursor.isNull(_cursorIndexOfPospselfphoto)) {
          _tmpPospselfphoto = null;
        } else {
          _tmpPospselfphoto = _cursor.getString(_cursorIndexOfPospselfphoto);
        }
        final String _tmpPospsenddesignation;
        if (_cursor.isNull(_cursorIndexOfPospsenddesignation)) {
          _tmpPospsenddesignation = null;
        } else {
          _tmpPospsenddesignation = _cursor.getString(_cursorIndexOfPospsenddesignation);
        }
        final String _tmpPospsendemail;
        if (_cursor.isNull(_cursorIndexOfPospsendemail)) {
          _tmpPospsendemail = null;
        } else {
          _tmpPospsendemail = _cursor.getString(_cursorIndexOfPospsendemail);
        }
        final String _tmpPospsendid;
        if (_cursor.isNull(_cursorIndexOfPospsendid)) {
          _tmpPospsendid = null;
        } else {
          _tmpPospsendid = _cursor.getString(_cursorIndexOfPospsendid);
        }
        final String _tmpPospsendmobile;
        if (_cursor.isNull(_cursorIndexOfPospsendmobile)) {
          _tmpPospsendmobile = null;
        } else {
          _tmpPospsendmobile = _cursor.getString(_cursorIndexOfPospsendmobile);
        }
        final String _tmpPospsendname;
        if (_cursor.isNull(_cursorIndexOfPospsendname)) {
          _tmpPospsendname = null;
        } else {
          _tmpPospsendname = _cursor.getString(_cursorIndexOfPospsendname);
        }
        final String _tmpPospsendphoto;
        if (_cursor.isNull(_cursorIndexOfPospsendphoto)) {
          _tmpPospsendphoto = null;
        } else {
          _tmpPospsendphoto = _cursor.getString(_cursorIndexOfPospsendphoto);
        }
        final String _tmpServiceurl;
        if (_cursor.isNull(_cursorIndexOfServiceurl)) {
          _tmpServiceurl = null;
        } else {
          _tmpServiceurl = _cursor.getString(_cursorIndexOfServiceurl);
        }
        final String _tmpShowmyinsurancebusiness;
        if (_cursor.isNull(_cursorIndexOfShowmyinsurancebusiness)) {
          _tmpShowmyinsurancebusiness = null;
        } else {
          _tmpShowmyinsurancebusiness = _cursor.getString(_cursorIndexOfShowmyinsurancebusiness);
        }
        final String _tmpUid;
        if (_cursor.isNull(_cursorIndexOfUid)) {
          _tmpUid = null;
        } else {
          _tmpUid = _cursor.getString(_cursorIndexOfUid);
        }
        final int _tmpUltralakshyaenabled;
        _tmpUltralakshyaenabled = _cursor.getInt(_cursorIndexOfUltralakshyaenabled);
        final String _tmpUserid;
        if (_cursor.isNull(_cursorIndexOfUserid)) {
          _tmpUserid = null;
        } else {
          _tmpUserid = _cursor.getString(_cursorIndexOfUserid);
        }
        _item = new ConstantEntity(_tmpFBAId,_tmpAddPospVisible,_tmpCVUrl,_tmpERPID,_tmpEliteKotakUrl,_tmpFinboxEnabled,_tmpFinperksEnabled,_tmpFourWheelerEnabled,_tmpFourWheelerUrl,_tmpFullName,_tmpGenerateMotorLeadsEnabled,_tmpHealthDemoUrl,_tmpHealthPopup,_tmpInvestmentEnabled,_tmpInvestmentUrl,_tmpIsDynamicDashEnabled,_tmpKotakEliteEnabled,_tmpLeadDashUrl,_tmpLoginID,_tmpManagName,_tmpMangEmail,_tmpMangMobile,_tmpMyMessagesEnabled,_tmpMyTransactionsEnabled,_tmpPBByCrnSearch,_tmpPOSPNo,_tmpPOSP_STATUS,_tmpPospappformEnabled,_tmpPospletterEnabled,_tmpRaiseTickitEnabled,_tmpRaiseTickitUrl,_tmpSmsTemplatesEnabled,_tmpStatus,_tmpSuppEmail,_tmpSuppMobile,_tmpTermPopup,_tmpTermPopupurl,_tmpTwoWheelerEnabled,_tmpTwoWheelerUrl,_tmpAddposplimit,_tmpAndroidproversion,_tmpBoempuid,_tmpCobrowserisactive,_tmpCobrowserlicensecode,_tmpCrnmobileno,_tmpEmplat,_tmpEmplng,_tmpEnableInsuranceBusiness,_tmpEnableenrolasposp,_tmpEnablemyaccountupdate,_tmpEnablencd,_tmpEnablesynccontact,_tmpFba_campaign_id,_tmpFba_campaign_name,_tmpFba_uid,_tmpFinboxurl,_tmpFinmartwhatsappno,_tmpFinperkurl,_tmpHdfc_code,_tmpHealthurl,_tmpHealthurltemp,_tmpInsurancerepositorylink,_tmpIosuid,_tmpIosversion,_tmpIsEmployee,_tmpLoanparentdesignation,_tmpLoanparentemail,_tmpLoanparentid,_tmpLoanparentmobile,_tmpLoanparentname,_tmpLoanparentphoto,_tmpLoanselfdesignation,_tmpLoanselfemail,_tmpLoanselfid,_tmpLoanselfmobile,_tmpLoanselfname,_tmpLoanselfphoto,_tmpLoansenddesignation,_tmpLoansendemail,_tmpLoansendid,_tmpLoansendmobile,_tmpLoansendname,_tmpLoansendphoto,_tmpMarketinghomedesciption,_tmpMarketinghomeenabled,_tmpMarketinghomemaxcount,_tmpMarketinghomepopupid,_tmpMarketinghometitle,_tmpMarketinghometransfertype,_tmpMarketinghomeurl,_tmpMessagesender,_tmpNotificationpopupurl,_tmpNotificationpopupurltype,_tmpPaenable,_tmpParentid,_tmpPlactive,_tmpPlbanner,_tmpPolicyByCRNEnabled,_tmpPospparentdesignation,_tmpPospparentemail,_tmpPospparentid,_tmpPospparentmobile,_tmpPospparentname,_tmpPospparentphoto,_tmpPospselfdesignation,_tmpPospselfemail,_tmpPospselfid,_tmpPospselfmobile,_tmpPospselfname,_tmpPospselfphoto,_tmpPospsenddesignation,_tmpPospsendemail,_tmpPospsendid,_tmpPospsendmobile,_tmpPospsendname,_tmpPospsendphoto,_tmpServiceurl,_tmpShowmyinsurancebusiness,_tmpUid,_tmpUltralakshyaenabled,_tmpUserid);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
