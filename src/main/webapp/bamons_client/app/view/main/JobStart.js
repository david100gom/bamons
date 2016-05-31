Ext.define('SpringBatch.view.main.JobStart', {

	extend: 'Ext.window.Window',
	controller: 'main',

    requires: [
        'Ext.form.Panel'
    ],

    frame: true,
    title: '특정 Job 구동',
    width: 400,
    bodyPadding: 10,
    layout: 'form',
	modal : true,

	items: {
		xtype: 'form',
		reference: 'jobForm',
		items: [{
			xtype: 'datefield',
			reference: 'targetDate',
			id: 'targetDate',
			name: 'targetDate',
			submitFormat: 'Y-m-d',
			format: 'Y-m-d',
			fieldLabel: 'Job 구동날짜',
			allowBlank: false
		},{
			xtype: 'textfield',
			reference: 'filePath',
			id: 'filePath',
			name: 'filePath',
			fieldLabel: '파일 위치',
			allowBlank: false,
			hidden: true
		},{
			xtype: 'combobox',
			reference: 'jobName',
			name : 'jobName',
			valueField: 'optionValue',
			fieldLabel: 'Job Name',
			displayField: 'optionText',
			anchor: '-15',
			store: {
				type: 'JobNameStore'
			},			
			minChars: 0,
			//queryMode: 'local',
			typeAhead: true,
			emptyText: 'Job Name을 선택하세요',
			allowBlank: false,
			listeners : {
				change: function(field, selectedValue) {
					if(selectedValue == "restoreRawDataJob") {
						Ext.getCmp('filePath').setVisible(true);
						Ext.getCmp('targetDate').setVisible(false);
						Ext.getCmp('filePath').enable();
						Ext.getCmp('targetDate').disable();
						
					}else{
						Ext.getCmp('filePath').setVisible(false);
						Ext.getCmp('targetDate').setVisible(true);
						Ext.getCmp('filePath').disable();
						Ext.getCmp('targetDate').enable();
					}
				}
			}
		}]
	},
	buttons: [{
        text   : 'Job 구동',
		handler: 'onStartClick'
    },{
        text : '닫기',
        handler : 'onStartWinCloseClick'
    }]
});