package com.util;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SmsUtils {

	// ��ʼ��ascClient��Ҫ�ļ�������
	final static String product = "Dysmsapi";// ����API��Ʒ���ƣ����Ų�Ʒ���̶��������޸ģ�
	final static String domain = "dysmsapi.aliyuncs.com";// ����API��Ʒ�������ӿڵ�ַ�̶��������޸ģ�
	// �滻�����AK
	final static String accessKeyId = "LTAIMbxYwtwGXLyq";// ���accessKeyId,�ο����ĵ�����2
	final static String accessKeySecret = "fV9H2abdHPdNVk9rvS0dxiXV2QKHbZ";// ���accessKeySecret���ο����ĵ�����2

	// ��ʼ��ascClient,��ʱ��֧�ֶ�region�������޸ģ�
	static IAcsClient acsClient;
	static {
		// ���ó�ʱʱ��-�����е���
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			throw new RuntimeException(e);
		}
		acsClient = new DefaultAcsClient(profile);
	}

	public static void send(String phone, String code) throws ServerException, ClientException {
		Map<String, String> body = new HashMap<String, String >();
		body.put("code", code);
		// ��װ�������
		SendSmsRequest request = new SendSmsRequest();
		// ʹ��post�ύ
		request.setMethod(MethodType.POST);
		// ����:�������ֻ��š�֧���Զ��ŷָ�����ʽ�����������ã���������Ϊ1000���ֻ�����,������������ڵ������ü�ʱ�������ӳ�,��֤�����͵Ķ����Ƽ�ʹ�õ������õķ�ʽ�����͹���/�۰�̨��Ϣʱ�����պ����ʽΪ00+��������+���룬�硰0085200000000��
		request.setPhoneNumbers(phone);
		// ����:����ǩ��-���ڶ��ſ���̨���ҵ�
		request.setSignName("�����ƶ��Ų���ר��");
		// ����:����ģ��-���ڶ��ſ���̨���ҵ������͹���/�۰�̨��Ϣʱ����ʹ�ù���/�۰�̨����ģ��
		request.setTemplateCode("SMS_139920142");
		// ��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
		// ������ʾ:���JSON����Ҫ�����з�,����ձ�׼��JSONЭ��Ի��з���Ҫ��,������������а���\r\n�������JSON����Ҫ��ʾ��\\r\\n,����ᵼ��JSON�ڷ���˽���ʧ��
		request.setTemplateParam(JSON.toJSONString(body));
		// ��ѡ-���ж�����չ��(��չ���ֶο�����7λ�����£������������û�����Դ��ֶ�)
		// request.setSmsUpExtendCode("90997");
		// ��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
		// request.setOutId("yourOutId");
		// ����ʧ���������ClientException�쳣
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			System.out.println("����ɹ���");
		}
	}

	public static void main(String[] args) throws ServerException, ClientException {
		String phone = "18701679616";
		String code = MathUtils.randomNumStr(6);
		SmsUtils.send(phone, code);
	}
}
