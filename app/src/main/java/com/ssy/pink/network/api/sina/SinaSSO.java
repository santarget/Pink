package com.ssy.pink.network.api.sina;


import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.alibaba.fastjson.JSONException;
import com.ssy.greendao.helper.HelperFactory;
import com.ssy.pink.bean.weibo.PreLoginInfo;
import com.ssy.pink.bean.weibo.RepostResult;
import com.ssy.pink.bean.weibo.WeiboLoginInfo;
import com.ssy.pink.utils.JsonUtils;
import com.ssy.pink.utils.ListUtils;

import net.sourceforge.tess4j.Tesseract;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.TextUtils;
import org.greenrobot.eventbus.EventBus;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SinaSSO {

    public String codeM = "";
    public String getServertimeUrl = "https://login.sina.com.cn/sso/prelogin.php?entry=weibo&callback=sinaSSOController.preloginCallBack&su=MTgzMTI0OTMxMDc%3D&rsakt=mod&checkpin=1&client=ssologin.js(v1.4.19)&_=";
    public String url = "https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.19)&_=";
    private final String sina_js = "var sinaSSOEncoder=sinaSSOEncoder||{};(function(){var hexcase=0;var chrsz=8;this.hex_sha1=function(s){return binb2hex(core_sha1(str2binb(s),s.length*chrsz));};var core_sha1=function(x,len){x[len>>5]|=0x80<<(24-len%32);x[((len+64>>9)<<4)+15]=len;var w=Array(80);var a=1732584193;var b=-271733879;var c=-1732584194;var d=271733878;var e=-1009589776;for(var i=0;i<x.length;i+=16){var olda=a;var oldb=b;var oldc=c;var oldd=d;var olde=e;for(var j=0;j<80;j++){if(j<16)w[j]=x[i+j];else w[j]=rol(w[j-3]^w[j-8]^w[j-14]^w[j-16],1);var t=safe_add(safe_add(rol(a,5),sha1_ft(j,b,c,d)),safe_add(safe_add(e,w[j]),sha1_kt(j)));e=d;d=c;c=rol(b,30);b=a;a=t;}a=safe_add(a,olda);b=safe_add(b,oldb);c=safe_add(c,oldc);d=safe_add(d,oldd);e=safe_add(e,olde);}return Array(a,b,c,d,e);};var sha1_ft=function(t,b,c,d){if(t<20)return(b&c)|((~b)&d);if(t<40)return b^c^d;if(t<60)return(b&c)|(b&d)|(c&d);return b^c^d;};var sha1_kt=function(t){return(t<20)?1518500249:(t<40)?1859775393:(t<60)?-1894007588:-899497514;};var safe_add=function(x,y){var lsw=(x&0xFFFF)+(y&0xFFFF);var msw=(x>>16)+(y>>16)+(lsw>>16);return(msw<<16)|(lsw&0xFFFF);};var rol=function(num,cnt){return(num<<cnt)|(num>>>(32-cnt));};var str2binb=function(str){var bin=Array();var mask=(1<<chrsz)-1;for(var i=0;i<str.length*chrsz;i+=chrsz)bin[i>>5]|=(str.charCodeAt(i/chrsz)&mask)<<(24-i%32);return bin;};var binb2hex=function(binarray){var hex_tab=hexcase?'0123456789ABCDEF':'0123456789abcdef';var str='';for(var i=0;i<binarray.length*4;i++){str+=hex_tab.charAt((binarray[i>>2]>>((3-i%4)*8+4))&0xF)+hex_tab.charAt((binarray[i>>2]>>((3-i%4)*8))&0xF);}return str;};this.base64={encode:function(input){input=''+input;if(input=='')return '';var output='';var chr1,chr2,chr3='';var enc1,enc2,enc3,enc4='';var i=0;do{chr1=input.charCodeAt(i++);chr2=input.charCodeAt(i++);chr3=input.charCodeAt(i++);enc1=chr1>>2;enc2=((chr1&3)<<4)|(chr2>>4);enc3=((chr2&15)<<2)|(chr3>>6);enc4=chr3&63;if(isNaN(chr2)){enc3=enc4=64;}else if(isNaN(chr3)){enc4=64;}output=output+this._keys.charAt(enc1)+this._keys.charAt(enc2)+this._keys.charAt(enc3)+this._keys.charAt(enc4);chr1=chr2=chr3='';enc1=enc2=enc3=enc4='';}while(i<input.length);return output;},_keys:'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/='};}).call(sinaSSOEncoder);;(function(){var dbits;var canary=0xdeadbeefcafe;var j_lm=((canary&0xffffff)==0xefcafe);function BigInteger(a,b,c){if(a!=null)if('number'==typeof a)this.fromNumber(a,b,c);else if(b==null && 'string' !=typeof a)this.fromString(a,256);else this.fromString(a,b);}function nbi(){return new BigInteger(null);}function am1(i,x,w,j,c,n){while(--n>=0){var v=x*this[i++]+w[j]+c;c=Math.floor(v/0x4000000);w[j++]=v&0x3ffffff;}return c;}function am2(i,x,w,j,c,n){var xl=x&0x7fff,xh=x>>15;while(--n>=0){var l=this[i]&0x7fff;var h=this[i++]>>15;var m=xh*l+h*xl;l=xl*l+((m&0x7fff)<<15)+w[j]+(c&0x3fffffff);c=(l>>>30)+(m>>>15)+xh*h+(c>>>30);w[j++]=l&0x3fffffff;}return c;}function am3(i,x,w,j,c,n){var xl=x&0x3fff,xh=x>>14;while(--n>=0){var l=this[i]&0x3fff;var h=this[i++]>>14;var m=xh*l+h*xl;l=xl*l+((m&0x3fff)<<14)+w[j]+c;c=(l>>28)+(m>>14)+xh*h;w[j++]=l&0xfffffff;}return c;}BigInteger.prototype.am=am3;dbits=28;BigInteger.prototype.DB=dbits;BigInteger.prototype.DM=((1<<dbits)-1);BigInteger.prototype.DV=(1<<dbits);var BI_FP=52;BigInteger.prototype.FV=Math.pow(2,BI_FP);BigInteger.prototype.F1=BI_FP-dbits;BigInteger.prototype.F2=2*dbits-BI_FP;var BI_RM='0123456789abcdefghijklmnopqrstuvwxyz';var BI_RC=new Array();var rr,vv;rr='0'.charCodeAt(0);for(vv=0;vv<=9;++vv)BI_RC[rr++]=vv;rr='a'.charCodeAt(0);for(vv=10;vv<36;++vv)BI_RC[rr++]=vv;rr='A'.charCodeAt(0);for(vv=10;vv<36;++vv)BI_RC[rr++]=vv;function int2char(n){return BI_RM.charAt(n);}function intAt(s,i){var c=BI_RC[s.charCodeAt(i)];return(c==null)?-1:c;}function bnpCopyTo(r){for(var i=this.t-1;i>=0;--i)r[i]=this[i];r.t=this.t;r.s=this.s;}function bnpFromInt(x){this.t=1;this.s=(x<0)?-1:0;if(x>0)this[0]=x;else if(x<-1)this[0]=x+DV;else this.t=0;}function nbv(i){var r=nbi();r.fromInt(i);return r;}function bnpFromString(s,b){var k;if(b==16)k=4;else if(b==8)k=3;else if(b==256)k=8;else if(b==2)k=1;else if(b==32)k=5;else if(b==4)k=2;else{this.fromRadix(s,b);return;}this.t=0;this.s=0;var i=s.length,mi=false,sh=0;while(--i>=0){var x=(k==8)?s[i]&0xff:intAt(s,i);if(x<0){if(s.charAt(i)=='-')mi=true;continue;}mi=false;if(sh==0)this[this.t++]=x;else if(sh+k>this.DB){this[this.t-1]|=(x&((1<<(this.DB-sh))-1))<<sh;this[this.t++]=(x>>(this.DB-sh));}else  this[this.t-1]|=x<<sh;sh+=k;if(sh>=this.DB)sh-=this.DB;}if(k==8&&(s[0]&0x80)!=0){this.s=-1;if(sh>0)this[this.t-1]|=((1<<(this.DB-sh))-1)<<sh;}this.clamp();if(mi)BigInteger.ZERO.subTo(this,this);}function bnpClamp(){var c=this.s&this.DM;while(this.t>0&&this[this.t-1]==c)--this.t;}function bnToString(b){if(this.s<0)return '-'+this.negate().toString(b);var k;if(b==16)k=4;else if(b==8)k=3;else if(b==2)k=1;else if(b==32)k=5;else if(b==4)k=2;else return this.toRadix(b);var km=(1<<k)-1,d,m=false,r='',i=this.t;var p=this.DB-(i*this.DB)%k;if(i-->0){if(p<this.DB&&(d=this[i]>>p)>0){m=true;r=int2char(d);}while(i>=0){if(p<k){d=(this[i]&((1<<p)-1))<<(k-p);d|=this[--i]>>(p+=this.DB-k);}else{d=(this[i]>>(p-=k))&km;if(p<=0){p+=this.DB;--i;}}if(d>0)m=true;if(m)r+=int2char(d);}}return m?r:'0';}function bnNegate(){var r=nbi();BigInteger.ZERO.subTo(this,r);return r;}function bnAbs(){return(this.s<0)?this.negate():this;}function bnCompareTo(a){var r=this.s-a.s;if(r!=0)return r;var i=this.t;r=i-a.t;if(r!=0)return r;while(--i>=0)if((r=this[i]-a[i])!=0)return r;return 0;}function nbits(x){var r=1,t;if((t=x>>>16)!=0){x=t;r+=16;}if((t=x>>8)!=0){x=t;r+=8;}if((t=x>>4)!=0){x=t;r+=4;}if((t=x>>2)!=0){x=t;r+=2;}if((t=x>>1)!=0){x=t;r+=1;}return r;}function bnBitLength(){if(this.t<=0)return 0;return this.DB*(this.t-1)+nbits(this[this.t-1]^(this.s&this.DM));}function bnpDLShiftTo(n,r){var i;for(i=this.t-1;i>=0;--i)r[i+n]=this[i];for(i=n-1;i>=0;--i)r[i]=0;r.t=this.t+n;r.s=this.s;}function bnpDRShiftTo(n,r){for(var i=n;i<this.t;++i)r[i-n]=this[i];r.t=Math.max(this.t-n,0);r.s=this.s;}function bnpLShiftTo(n,r){var bs=n%this.DB;var cbs=this.DB-bs;var bm=(1<<cbs)-1;var ds=Math.floor(n/this.DB),c=(this.s<<bs)&this.DM,i;for(i=this.t-1;i>=0;--i){r[i+ds+1]=(this[i]>>cbs)|c;c=(this[i]&bm)<<bs;}for(i=ds-1;i>=0;--i)r[i]=0;r[ds]=c;r.t=this.t+ds+1;r.s=this.s;r.clamp();}function bnpRShiftTo(n,r){r.s=this.s;var ds=Math.floor(n/this.DB);if(ds>=this.t){r.t=0;return;}var bs=n%this.DB;var cbs=this.DB-bs;var bm=(1<<bs)-1;r[0]=this[ds]>>bs;for(var i=ds+1;i<this.t;++i){r[i-ds-1]|=(this[i]&bm)<<cbs;r[i-ds]=this[i]>>bs;}if(bs>0)r[this.t-ds-1]|=(this.s&bm)<<cbs;r.t=this.t-ds;r.clamp();}function bnpSubTo(a,r){var i=0,c=0,m=Math.min(a.t,this.t);while(i<m){c+=this[i]-a[i];r[i++]=c&this.DM;c>>=this.DB;}if(a.t<this.t){c-=a.s;while(i<this.t){c+=this[i];r[i++]=c&this.DM;c>>=this.DB;}c+=this.s;}else{c+=this.s;while(i<a.t){c-=a[i];r[i++]=c&this.DM;c>>=this.DB;}c-=a.s;}r.s=(c<0)?-1:0;if(c<-1)r[i++]=this.DV+c;else if(c>0)r[i++]=c;r.t=i;r.clamp();}function bnpMultiplyTo(a,r){var x=this.abs(),y=a.abs();var i=x.t;r.t=i+y.t;while(--i>=0)r[i]=0;for(i=0;i<y.t;++i)r[i+x.t]=x.am(0,y[i],r,i,0,x.t);r.s=0;r.clamp();if(this.s!=a.s)BigInteger.ZERO.subTo(r,r);}function bnpSquareTo(r){var x=this.abs();var i=r.t=2*x.t;while(--i>=0)r[i]=0;for(i=0;i<x.t-1;++i){var c=x.am(i,x[i],r,2*i,0,1);if((r[i+x.t]+=x.am(i+1,2*x[i],r,2*i+1,c,x.t-i-1))>=x.DV){r[i+x.t]-=x.DV;r[i+x.t+1]=1;}}if(r.t>0)r[r.t-1]+=x.am(i,x[i],r,2*i,0,1);r.s=0;r.clamp();}function bnpDivRemTo(m,q,r){var pm=m.abs();if(pm.t<=0)return;var pt=this.abs();if(pt.t<pm.t){if(q!=null)q.fromInt(0);if(r!=null)this.copyTo(r);return;}if(r==null)r=nbi();var y=nbi(),ts=this.s,ms=m.s;var nsh=this.DB-nbits(pm[pm.t-1]);if(nsh>0){pm.lShiftTo(nsh,y);pt.lShiftTo(nsh,r);}else{pm.copyTo(y);pt.copyTo(r);}var ys=y.t;var y0=y[ys-1];if(y0==0)return;var yt=y0*(1<<this.F1)+((ys>1)?y[ys-2]>>this.F2:0);var d1=this.FV/yt,d2=(1<<this.F1)/yt,e=1<<this.F2;var i=r.t,j=i-ys,t=(q==null)?nbi():q;y.dlShiftTo(j,t);if(r.compareTo(t)>=0){r[r.t++]=1;r.subTo(t,r);}BigInteger.ONE.dlShiftTo(ys,t);t.subTo(y,y);while(y.t<ys)y[y.t++]=0;while(--j>=0){var qd=(r[--i]==y0)?this.DM:Math.floor(r[i]*d1+(r[i-1]+e)*d2);if((r[i]+=y.am(0,qd,r,j,0,ys))<qd){y.dlShiftTo(j,t);r.subTo(t,r);while(r[i]<--qd)r.subTo(t,r);}}if(q!=null){r.drShiftTo(ys,q);if(ts!=ms)BigInteger.ZERO.subTo(q,q);}r.t=ys;r.clamp();if(nsh>0)r.rShiftTo(nsh,r);if(ts<0)BigInteger.ZERO.subTo(r,r);}function bnMod(a){var r=nbi();this.abs().divRemTo(a,null,r);if(this.s<0&&r.compareTo(BigInteger.ZERO)>0)a.subTo(r,r);return r;}function Classic(m){this.m=m;}function cConvert(x){if(x.s<0||x.compareTo(this.m)>=0)return x.mod(this.m);else return x;}function cRevert(x){return x;}function cReduce(x){x.divRemTo(this.m,null,x);}function cMulTo(x,y,r){x.multiplyTo(y,r);this.reduce(r);}function cSqrTo(x,r){x.squareTo(r);this.reduce(r);}Classic.prototype.convert=cConvert;Classic.prototype.revert=cRevert;Classic.prototype.reduce=cReduce;Classic.prototype.mulTo=cMulTo;Classic.prototype.sqrTo=cSqrTo;function bnpInvDigit(){if(this.t<1)return 0;var x=this[0];if((x&1)==0)return 0;var y=x&3;y=(y*(2-(x&0xf)*y))&0xf;y=(y*(2-(x&0xff)*y))&0xff;y=(y*(2-(((x&0xffff)*y)&0xffff)))&0xffff;y=(y*(2-x*y%this.DV))%this.DV;return(y>0)?this.DV-y:-y;}function Montgomery(m){this.m=m;this.mp=m.invDigit();this.mpl=this.mp&0x7fff;this.mph=this.mp>>15;this.um=(1<<(m.DB-15))-1;this.mt2=2*m.t;}function montConvert(x){var r=nbi();x.abs().dlShiftTo(this.m.t,r);r.divRemTo(this.m,null,r);if(x.s<0&&r.compareTo(BigInteger.ZERO)>0)this.m.subTo(r,r);return r;}function montRevert(x){var r=nbi();x.copyTo(r);this.reduce(r);return r;}function montReduce(x){while(x.t<=this.mt2)x[x.t++]=0;for(var i=0;i<this.m.t;++i){var j=x[i]&0x7fff;var u0=(j*this.mpl+(((j*this.mph+(x[i]>>15)*this.mpl)&this.um)<<15))&x.DM;j=i+this.m.t;x[j]+=this.m.am(0,u0,x,i,0,this.m.t);while(x[j]>=x.DV){x[j]-=x.DV;x[++j]++;}}x.clamp();x.drShiftTo(this.m.t,x);if(x.compareTo(this.m)>=0)x.subTo(this.m,x);}function montSqrTo(x,r){x.squareTo(r);this.reduce(r);}function montMulTo(x,y,r){x.multiplyTo(y,r);this.reduce(r);}Montgomery.prototype.convert=montConvert;Montgomery.prototype.revert=montRevert;Montgomery.prototype.reduce=montReduce;Montgomery.prototype.mulTo=montMulTo;Montgomery.prototype.sqrTo=montSqrTo;function bnpIsEven(){return((this.t>0)?(this[0]&1):this.s)==0;}function bnpExp(e,z){if(e>0xffffffff||e<1)return BigInteger.ONE;var r=nbi(),r2=nbi(),g=z.convert(this),i=nbits(e)-1;g.copyTo(r);while(--i>=0){z.sqrTo(r,r2);if((e&(1<<i))>0)z.mulTo(r2,g,r);else{var t=r;r=r2;r2=t;}}return z.revert(r);}function bnModPowInt(e,m){var z;if(e<256||m.isEven())z=new Classic(m);else z=new Montgomery(m);return this.exp(e,z);}BigInteger.prototype.copyTo=bnpCopyTo;BigInteger.prototype.fromInt=bnpFromInt;BigInteger.prototype.fromString=bnpFromString;BigInteger.prototype.clamp=bnpClamp;BigInteger.prototype.dlShiftTo=bnpDLShiftTo;BigInteger.prototype.drShiftTo=bnpDRShiftTo;BigInteger.prototype.lShiftTo=bnpLShiftTo;BigInteger.prototype.rShiftTo=bnpRShiftTo;BigInteger.prototype.subTo=bnpSubTo;BigInteger.prototype.multiplyTo=bnpMultiplyTo;BigInteger.prototype.squareTo=bnpSquareTo;BigInteger.prototype.divRemTo=bnpDivRemTo;BigInteger.prototype.invDigit=bnpInvDigit;BigInteger.prototype.isEven=bnpIsEven;BigInteger.prototype.exp=bnpExp;BigInteger.prototype.toString=bnToString;BigInteger.prototype.negate=bnNegate;BigInteger.prototype.abs=bnAbs;BigInteger.prototype.compareTo=bnCompareTo;BigInteger.prototype.bitLength=bnBitLength;BigInteger.prototype.mod=bnMod;BigInteger.prototype.modPowInt=bnModPowInt;BigInteger.ZERO=nbv(0);BigInteger.ONE=nbv(1);function Arcfour(){this.i=0;this.j=0;this.S=new Array();}function ARC4init(key){var i,j,t;for(i=0;i<256;++i)this.S[i]=i;j=0;for(i=0;i<256;++i){j=(j+this.S[i]+key[i%key.length])&255;t=this.S[i];this.S[i]=this.S[j];this.S[j]=t;}this.i=0;this.j=0;}function ARC4next(){var t;this.i=(this.i+1)&255;this.j=(this.j+this.S[this.i])&255;t=this.S[this.i];this.S[this.i]=this.S[this.j];this.S[this.j]=t;return this.S[(t+this.S[this.i])&255];}Arcfour.prototype.init=ARC4init;Arcfour.prototype.next=ARC4next;function prng_newstate(){return new Arcfour();}var rng_psize=256;var rng_state;var rng_pool;var rng_pptr;function rng_seed_int(x){rng_pool[rng_pptr++]^=x&255;rng_pool[rng_pptr++]^=(x>>8)&255;rng_pool[rng_pptr++]^=(x>>16)&255;rng_pool[rng_pptr++]^=(x>>24)&255;if(rng_pptr>=rng_psize)rng_pptr-=rng_psize;}function rng_seed_time(){rng_seed_int(new Date().getTime());}if(rng_pool==null){rng_pool=new Array();rng_pptr=0;var t;while(rng_pptr<rng_psize){t=Math.floor(65536*Math.random());rng_pool[rng_pptr++]=t>>>8;rng_pool[rng_pptr++]=t&255;}rng_pptr=0;rng_seed_time();}function rng_get_byte(){if(rng_state==null){rng_seed_time();rng_state=prng_newstate();rng_state.init(rng_pool);for(rng_pptr=0;rng_pptr<rng_pool.length;++rng_pptr)rng_pool[rng_pptr]=0;rng_pptr=0;}return rng_state.next();}function rng_get_bytes(ba){var i;for(i=0;i<ba.length;++i)ba[i]=rng_get_byte();}function SecureRandom(){}SecureRandom.prototype.nextBytes=rng_get_bytes;function parseBigInt(str,r){return new BigInteger(str,r);}function linebrk(s,n){var ret='';var i=0;while(i+n<s.length){ret+=s.substring(i,i+n)+'\\n';i+=n;}return ret+s.substring(i,s.length);}function byte2Hex(b){if(b<0x10)return '0'+b.toString(16);else  return b.toString(16);}function pkcs1pad2(s,n){if(n<s.length+11){return null;}var ba=new Array();var i=s.length-1;while(i>=0&&n>0){var c=s.charCodeAt(i--);if(c<128){ba[--n]=c;}else if((c>127)&&(c<2048)){ba[--n]=(c&63)|128;ba[--n]=(c>>6)|192;}else{ba[--n]=(c&63)|128;ba[--n]=((c>>6)&63)|128;ba[--n]=(c>>12)|224;}}ba[--n]=0;var rng=new SecureRandom();var x=new Array();while(n>2){x[0]=0;while(x[0]==0)rng.nextBytes(x);ba[--n]=x[0];}ba[--n]=2;ba[--n]=0;return new BigInteger(ba);}function RSAKey(){this.n=null;this.e=0;this.d=null;this.p=null;this.q=null;this.dmp1=null;this.dmq1=null;this.coeff=null;}function RSASetPublic(N,E){if(N!=null&&E!=null&&N.length>0&&E.length>0){this.n=parseBigInt(N,16);this.e=parseInt(E,16);}else alert('Invalid RSA public key');}function RSADoPublic(x){return x.modPowInt(this.e,this.n);}function RSAEncrypt(text){var m=pkcs1pad2(text,(this.n.bitLength()+7)>>3);if(m==null)return null;var c=this.doPublic(m);if(c==null)return null;var h=c.toString(16);if((h.length&1)==0)return h;else return '0'+h;}RSAKey.prototype.doPublic=RSADoPublic;RSAKey.prototype.setPublic=RSASetPublic;RSAKey.prototype.encrypt=RSAEncrypt;this.RSAKey=RSAKey;}).call(sinaSSOEncoder);function getpass(pwd,servicetime,nonce,rsaPubkey){var RSAKey=new sinaSSOEncoder.RSAKey();RSAKey.setPublic(rsaPubkey,'10001');var password=RSAKey.encrypt([servicetime,nonce].join('\\t')+'\\n'+pwd);return password;}";

    private String door = "";

    private SinaSSO() {
    }

    private static SinaSSO instance;

    public static SinaSSO getInstance() {
        if (instance == null) {
            synchronized (SinaSSO.class) {
                if (instance == null) {
                    instance = new SinaSSO();
                }
            }
        }
        return instance;
    }

    public void setDoor(String door) {
        this.door = door;
        Log.i("aaaa", "设置验证码：" + door);
    }

    // 1.下载验证码：将多个验证码图片下载到指定目录，要求各种可能的验证码（单个数字）都应该有，比如：0-9。
    public File downloadImage(PreLoginInfo preLoginInfo) {
        String r = String.valueOf(Math.floor((Math.random()) * 100000000));
        String urlpath = "https://login.sina.com.cn/cgi/pin.php?r=" + r + "&s=0&p=" + preLoginInfo.getPcid();
        Log.i("aaaa", "urlpath:" + urlpath);

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getMethod = new HttpGet(urlpath);
//            try {
        try {
            HttpResponse response = httpClient.execute(getMethod, new BasicHttpContext());
            HttpEntity entity = response.getEntity();
            InputStream instream = entity.getContent();
            File file = new File(Environment.getExternalStorageDirectory() + "/1/", "verify_code.png");
            if (file.exists()) {
                file.delete();
            }
            OutputStream outstream = new FileOutputStream(file);
            int l = -1;
            byte[] tmp = new byte[2048];
            while ((l = instream.read(tmp)) != -1) {
                outstream.write(tmp);
            }
            outstream.close();
            Log.i("aaaa", "下载验证码完毕！");
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public WeiboLoginInfo login(String name, String password) throws IOException {
        PreLoginInfo preLoginInfo = getServerInfo();
        if (preLoginInfo == null) {
            return null;
        }
        String su = new String(Base64.encode(name.getBytes(), Base64.DEFAULT));
        String sp = encodePassword(password, preLoginInfo);
        HttpPost post = null;
        HttpClient httpClient = new DefaultHttpClient();
        url += codeM;
        post = new HttpPost(url);
        post.setHeader("Content-type", "application/x-www-form-urlencoded");
        post.setHeader("Access-Control-Allow-Credentials", "true");
        post.setHeader("Access-Control-Allow-Origin", "http://my.sina.com.cn");
        List<NameValuePair> list = new ArrayList<>();

        list.add(new BasicNameValuePair("cdult", "3"));
        list.add(new BasicNameValuePair("domain", "sina.com.cn"));
        if (!TextUtils.isEmpty(door)) {
            list.add(new BasicNameValuePair("door", door));
        }
        list.add(new BasicNameValuePair("entry", "homepage"));
        list.add(new BasicNameValuePair("gateway", "1"));
        list.add(new BasicNameValuePair("from", ""));
        list.add(new BasicNameValuePair("savestate", "30"));
        list.add(new BasicNameValuePair("useticket", "0"));
        list.add(new BasicNameValuePair("vsnf", "1"));
        list.add(new BasicNameValuePair("ssosimplelogin", "1"));
        list.add(new BasicNameValuePair("su", su));
        list.add(new BasicNameValuePair("service", "sso"));
        list.add(new BasicNameValuePair("servertime", preLoginInfo.getServertime()));
        list.add(new BasicNameValuePair("nonce", preLoginInfo.getNonce()));
        list.add(new BasicNameValuePair("pwencode", "rsa2"));
        list.add(new BasicNameValuePair("rsakv", preLoginInfo.getRsakv()));
        list.add(new BasicNameValuePair("sp", sp));
        list.add(new BasicNameValuePair("encoding", "UTF-8"));
        list.add(new BasicNameValuePair("prelt", "162"));
        list.add(new BasicNameValuePair("url", "http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack"));
        list.add(new BasicNameValuePair("returntype", "TEXT"));
        list.add(new BasicNameValuePair("sr", "1366*768"));
        list.add(new BasicNameValuePair("pagerefer", "http://my.sina.com.cn/profile/logined"));
        list.add(new BasicNameValuePair("qrcode_flag", "true"));
        list.add(new BasicNameValuePair("__proto__", "Object"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String result = EntityUtils.toString(resEntity, "UTF-8");
                if (!TextUtils.isEmpty(result)) {
                    WeiboLoginInfo weiboLoginInfo = JsonUtils.toObject(result, WeiboLoginInfo.class);
                    Log.i("aaaa", "weiboLoginInfo：" + weiboLoginInfo.toString());
                    return weiboLoginInfo;
                }
            }
        }
        return null;
    }

    public RepostInfo repost(RepostInfo repostInfo, String weiboId, String reason) {
        HttpClient httpClient;
        if (repostInfo.getHttpClient() == null) {
            httpClient = new DefaultHttpClient();
            repostInfo.setHttpClient(httpClient);
        } else {
            httpClient = repostInfo.getHttpClient();
        }
        if (repostInfo.getWeiboLoginInfo() == null) {
            try {
                WeiboLoginInfo weiboLoginInfo = login(repostInfo.smallInfo.getSmallWeiboNum(), repostInfo.smallInfo.getUsepwd());
                if (weiboLoginInfo != null && weiboLoginInfo.getRetcode().equals("0")) {
                    repostInfo.setWeiboLoginInfo(weiboLoginInfo);
                    HelperFactory.getWeiboLoginDbHelper().insertOrReplace(weiboLoginInfo);
                } else {
                    repostInfo.setRepostResult(new RepostResult(weiboLoginInfo == null ? "微博登录失败" : weiboLoginInfo.getReason()));
                    return repostInfo;
                }
            } catch (IOException e) {
                repostInfo.setRepostResult(new RepostResult("微博登录失败"));
                e.printStackTrace();
                return repostInfo;
            }
        }
        if (repostInfo.getSsoResponse() == null) {
            List<String> urlList = repostInfo.getWeiboLoginInfo().getCrossDomainUrlList();
            if (ListUtils.isEmpty(urlList)) {
                repostInfo.setRepostResult(new RepostResult("鉴权url为空"));
                return repostInfo;
            }
            String weibourl = (String) urlList.get(0);
            HttpGet getsso = new HttpGet(weibourl);
            try {
                HttpResponse ssoResponse = httpClient.execute(getsso);
                Log.i("aaaa", "sso info = " + EntityUtils.toString(ssoResponse.getEntity(), "UTF-8"));
                repostInfo.setSsoResponse(ssoResponse);
                //({"result":true,"userinfo":{"uniqueid":"6636712991","displayname":"_nuoxiya2002"}});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            //请求转发请求参数
            String postZ = "https://weibo.com/aj/v6/mblog/forward?ajwvr=6&domain=6636712991&__rnd=" + Calendar.getInstance().getTimeInMillis();
            HttpPost postz = new HttpPost(postZ);
            postz.setHeader("Content-type", "application/x-www-form-urlencoded");
            postz.setHeader("Host", "weibo.com");
            postz.setHeader("Referer", "https://weibo.com/u/6636712991/home?topnav=1&wvr=6");
            List<NameValuePair> listParam = new ArrayList<NameValuePair>();
            listParam.add(new BasicNameValuePair("_t", "0"));
            listParam.add(new BasicNameValuePair("appkey", ""));
            listParam.add(new BasicNameValuePair("from_plugin", "0"));
            listParam.add(new BasicNameValuePair("group_source", "group_all"));
            listParam.add(new BasicNameValuePair("isReEdit", "false"));
            listParam.add(new BasicNameValuePair("location", "page_100206_home"));
            listParam.add(new BasicNameValuePair("mark", "1_12D077FC3E70881A4D5B5F159E8AA51119F0B8914BE1BCFA127D1AF92F8D74FFC1F1406A824F98FFDA6285141BC789DDC0FAEF719ED86D689C63C3E4887B3142FEFF915263D2A3097EC0DFB90FF89CACDDD2CF8DE37BF800B8D58EE4449207634BD91DD382074A8547B9C8E79B8EDD85"));
            listParam.add(new BasicNameValuePair("mid", weiboId));//被转发的微博id
            listParam.add(new BasicNameValuePair("module", ""));
            listParam.add(new BasicNameValuePair("page_module_id", ""));
            listParam.add(new BasicNameValuePair("pdetail", "1002061642909335"));
            listParam.add(new BasicNameValuePair("pic_id", ""));
            listParam.add(new BasicNameValuePair("pic_src", ""));
            listParam.add(new BasicNameValuePair("rank", "0"));
            listParam.add(new BasicNameValuePair("rankid", ""));
            listParam.add(new BasicNameValuePair("reason", reason));
            listParam.add(new BasicNameValuePair("refer_sort", ""));
            listParam.add(new BasicNameValuePair("rid", "0_0_0_3071694476221415112_0_0"));
            listParam.add(new BasicNameValuePair("style_type", "1"));
            UrlEncodedFormEntity entityz = new UrlEncodedFormEntity(listParam, "UTF-8");
            postz.setEntity(entityz);
            HttpResponse responsez = httpClient.execute(postz);
            if (responsez != null) {
                HttpEntity resEntityz = responsez.getEntity();
                if (resEntityz != null) {
                    String resultz = EntityUtils.toString(resEntityz, "UTF-8");
                    try {
                        RepostResult repostResult = JsonUtils.toObject(resultz, RepostResult.class);
                        Log.i("aaaa", "repostResult:" + repostResult.toString());
                        repostInfo.setRepostResult(repostResult);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        repostInfo.setRepostResult(new RepostResult(RepostResult.ERROR_RELOAD, "尝试重新登录"));
                        HelperFactory.getWeiboLoginDbHelper().delete(repostInfo.getWeiboLoginInfo());
                        repostInfo.setWeiboLoginInfo(null);
                    }
                } else {
                    repostInfo.setRepostResult(new RepostResult("responsez HttpEntity is null:" + responsez.toString()));
                }
            } else {
                repostInfo.setRepostResult(new RepostResult("responsez is null"));
            }
        } catch (IOException e) {
            repostInfo.setRepostResult(new RepostResult("repost exception:" + e.getMessage()));
            e.printStackTrace();
        }
        return repostInfo;
    }


    private String encodePassword(String password, PreLoginInfo info) {
        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        try {
            Scriptable scope = rhino.initStandardObjects();

            ScriptableObject.putProperty(scope, "javaContext", Context.javaToJS(this, scope));
            ScriptableObject.putProperty(scope, "javaLoader", Context.javaToJS(SinaSSO.class.getClassLoader(), scope));

            rhino.evaluateString(scope, sina_js, "SinaSSO", 1, null);

            Function function = (Function) scope.get("getpass", scope);
            Object[] functionParams = new Object[]{password, info.getServertime(), info.getNonce(),
                    info.getPubkey()};
            Object result = function.call(rhino, scope, scope, functionParams);
            if (result instanceof String) {
                return (String) result;
            } else if (result instanceof NativeJavaObject) {
                return (String) ((NativeJavaObject) result).getDefaultValue(String.class);
            } else if (result instanceof NativeObject) {
                return (String) ((NativeObject) result).getDefaultValue(String.class);
            }
            return result.toString();//(String) function.call(rhino, scope, scope, functionParams);
        } finally {
            Context.exit();
        }
    }

    /**
     * 获取登录需要的（rsakv、servertime、nonce）
     *
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public PreLoginInfo getServerInfo() {
        codeM = String.valueOf(Calendar.getInstance().getTimeInMillis());
        getServertimeUrl += codeM;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getssoConfig = new HttpGet(getServertimeUrl);
        PreLoginInfo preLoginInfo = null;
        try {
            HttpResponse getssoresponse = httpClient.execute(getssoConfig);
            HttpEntity resEntity = getssoresponse.getEntity();
            String result = EntityUtils.toString(resEntity, "UTF-8");
            String importantText = result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1);
            preLoginInfo = JsonUtils.toObject(importantText, PreLoginInfo.class);
            Log.i("aaaa", "preLoginInfo:" + preLoginInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return preLoginInfo;
    }

    public WeiboLoginInfo testLogin(PreLoginInfo preLoginInfo, String name, String password, String door) {
        String su = new String(Base64.encode(name.getBytes(), Base64.DEFAULT));
        String sp = encodePassword(password, preLoginInfo);
        HttpPost post = null;
        HttpClient httpClient = new DefaultHttpClient();
        url += codeM;
        post = new HttpPost(url);
        post.setHeader("Content-type", "application/x-www-form-urlencoded");
        post.setHeader("Access-Control-Allow-Credentials", "true");
        post.setHeader("Access-Control-Allow-Origin", "http://my.sina.com.cn");
        List<NameValuePair> list = new ArrayList<>();

        list.add(new BasicNameValuePair("cdult", "3"));
        list.add(new BasicNameValuePair("domain", "sina.com.cn"));
        if (!TextUtils.isEmpty(door)) {
            list.add(new BasicNameValuePair("door", door));
        }
        list.add(new BasicNameValuePair("pcid", preLoginInfo.getPcid()));
        list.add(new BasicNameValuePair("entry", "homepage"));
        list.add(new BasicNameValuePair("gateway", "1"));
        list.add(new BasicNameValuePair("from", ""));
        list.add(new BasicNameValuePair("savestate", "30"));
        list.add(new BasicNameValuePair("useticket", "0"));
        list.add(new BasicNameValuePair("vsnf", "1"));
        list.add(new BasicNameValuePair("ssosimplelogin", "1"));
        list.add(new BasicNameValuePair("su", su));
        list.add(new BasicNameValuePair("service", "sso"));
        list.add(new BasicNameValuePair("servertime", preLoginInfo.getServertime()));
        list.add(new BasicNameValuePair("nonce", preLoginInfo.getNonce()));
        list.add(new BasicNameValuePair("pwencode", "rsa2"));
        list.add(new BasicNameValuePair("rsakv", preLoginInfo.getRsakv()));
        list.add(new BasicNameValuePair("sp", sp));
        list.add(new BasicNameValuePair("encoding", "UTF-8"));
        list.add(new BasicNameValuePair("prelt", "162"));
        list.add(new BasicNameValuePair("url", "http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack"));
        list.add(new BasicNameValuePair("returntype", "TEXT"));
        list.add(new BasicNameValuePair("sr", "1366*768"));
        list.add(new BasicNameValuePair("pagerefer", "http://my.sina.com.cn/profile/logined"));
        list.add(new BasicNameValuePair("qrcode_flag", "true"));
        list.add(new BasicNameValuePair("__proto__", "Object"));
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String result = EntityUtils.toString(resEntity, "UTF-8");
                    if (!TextUtils.isEmpty(result)) {
                        WeiboLoginInfo weiboLoginInfo = JsonUtils.toObject(result, WeiboLoginInfo.class);
                        Log.i("aaaa", "weiboLoginInfo：" + weiboLoginInfo.toString());
                        return weiboLoginInfo;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
