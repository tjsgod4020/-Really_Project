#koreaexim open api ����

https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=5bC4hLlng7kzfx5HpjA8lJFvs6ehjlV9&searchdate=&data=AP01

��û���� (Requester Parameter)

������		Ÿ��		��������	������
authkey		String(�ʼ�)	����Ű	OpenAPI ��û�� �߱޵� ����Ű
searchdate	String		�˻���û��¥	ex) 2015-01-01, 20150101, (DEFAULT)������
data		String(�ʼ�)	�˻���ûAPIŸ��	AP01 : ȯ��, AP02 : ����ݸ�, AP03 : �����ݸ�

��°�� (Response Element)

������	Ÿ��	��������	������
RESULT		Integer	��ȸ ���	1 : ����, 2 : DATA�ڵ� ����, 3 : �����ڵ� ����, 4 : ��������Ƚ�� ����
CUR_UNIT	String	��ȭ�ڵ�	��
CUR_NM		String	����/��ȭ��	��
TTB		String	����ȯ(�۱�)�����Ƕ�	��
TTS		String	����ȯ(�۱�)�����Ƕ�	��
DEAL_BAS_R	String	�Ÿ� ������	��
BKPR		String	��ΰ���	��
YY_EFEE_R	String	��ȯ������	��
TEN_DD_EFEE_R	String	10��ȯ������	��
KFTC_DEAL_BAS_R	String	����ܱ�ȯ�߰�Ÿű�����	?
KFTC_BKPR	String	����ܱ�ȯ�߰���ΰ���	��

�̿�� ���ǻ���

�񿵾����� ������, Ȥ�� �������� 11�� ������ �ش����� �����͸� ��û�� ��� null ���� ��ȯ