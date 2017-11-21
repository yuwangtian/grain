SELECT
	gd.`name` '����',
	gx. NAME 'С��',
	u. NAME '����',
	CASE u.sex
WHEN '0' THEN
	'Ů'
WHEN '1' THEN
	'��'
END AS '�Ա�',
 u.phone '�绰',
 u.email '�ʼ�',
 u.remark '��ע'
FROM
	grain_user u
LEFT JOIN grain_group g ON u.group_id = g.group_id
LEFT JOIN grain_group gx ON g.parent_id = gx.group_id
LEFT JOIN grain_group gd ON gx.parent_id = gd.group_id
WHERE
	g. CODE LIKE '0626%'
ORDER BY
	gd.`name`,
	gx. NAME