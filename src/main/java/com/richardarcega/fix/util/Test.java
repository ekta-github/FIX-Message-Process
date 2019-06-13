package com.richardarcega.fix.util;

import quickfix.InvalidMessage;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String messageString = "8=FIX.4.49=12235=D34=21549=CLIENT1252=20100225-19:41:57.31656=B1=Marcel11=1334621=140=255=MSFT38=144=554=159=060=20100225-19:39:52.02010=4";
		String messageString =   "8=FIX.4.49=143435=C|34=11919|49=LQNTACCNT|52=20190425-16:20:55.664|56=SGACCNT|94=0|147=UPDATE Account|164=25122055|33=1|58=<AcctOpngInstr><MsgId><Id>1556209255662</Id><CreDtTm>2019-04-25T12:20:55.662068</CreDtTm></MsgId><PrvsRef><Ref>120223</Ref></PrvsRef><InstrDtls><OpngTp>NEWA</OpngTp><AcctApplId>FRONTEME</AcctApplId><ClntRef>MSAM</ClntRef><CtrPtyRef><Ref>323</Ref></CtrPtyRef></InstrDtls><InvstmtAcct><Id><Prtry><Id>120223</Id></Prtry></Id><OwnrshTp><Cd>CORP</Cd></OwnrshTp><StmtFrqcy><Cd>ONDE</Cd></StmtFrqcy></InvstmtAcct><AcctPties><PrncplAcctPty><PmryOwnr><Pty><Org><Nm>MS Galaxy Fund - Frontier Emerging Markets Fund</Nm><TaxId><Id>26-2681022</Id><TaxIdTp><Cd>GTIN</Cd></TaxIdTp><IssrCtry>XX</IssrCtry></TaxId><PstlAdr><AdrTp>BIZZ</AdrTp><MlngInd>0</MlngInd><RegnAdrInd>0</RegnAdrInd><NmAndAdr><Adr><AdrTp>ADDR</AdrTp><AdrLine>522 5th Avenue</AdrLine><AdrLine>4th Floor</AdrLine><PstCd>10036</PstCd><TwnNm>New York</TwnNm><CtrySubDvsn>NY</CtrySubDvsn><Ctry>US</Ctry></Adr></NmAndAdr></PstlAdr><PmryComAdr/><AddtlRgltryInf><Othr>16036806-0000</Othr></AddtlRgltryInf></Org></Pty></PmryOwnr></PrncplAcctPty><ScndryOwnr><Pty><Org><Nm>-</Nm><PstlAdr><AdrTp>BIZZ</AdrTp><MlngInd>0</MlngInd><RegnAdrInd>0</RegnAdrInd><NmAndAdr><Adr><Ctry>XX</Ctry></Adr></NmAndAdr></PstlAdr></Org></Pty></ScndryOwnr></AcctPties><Xtnsn><PlcAndNm>CONF_SUPP</PlcAndNm><Txt>N</Txt></Xtnsn><Xtnsn><PlcAndNm>BPS_ID</PlcAndNm><Txt>49120223</Txt></Xtnsn></AcctOpngInstr>|10=084|";
		
		String withSOH = messageString.replace('|', '\001');
		
		System.out.print("withSOH" + withSOH);
		
		String messageStringNew = messageString.replace("|","");
		
		System.out.print("messageStringNew" + messageStringNew);
		
		int messageTypeEnd = 19;
		while (messageString.charAt(messageTypeEnd) != '\001') {
			System.out.print("Inside " + messageString.charAt(messageTypeEnd) +  '\n');
		    messageTypeEnd++;
		    System.out.print("messageTypeEnd " + messageTypeEnd +  '\n');
		    System.out.print("After " + messageString.charAt(messageTypeEnd) + '\n');
		}
		
	}

}
