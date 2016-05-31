Ext.define('SpringBatch.view.main.JobStepInfo', {

    extend: 'Ext.window.Window',
    controller: 'main',

    requires: [
        'Ext.form.Panel',
		'Ext.layout.container.Column'
    ],

	title : 'Job & Step 정보',
    frame: true,
    width: 1200,
    bodyPadding: 5,

    fieldDefaults: {
        labelAlign: 'left',
        labelWidth: 100,
        anchor: '100%'
    },
	modal : true,
    stores:['BatchStepExecutionStore'],

    initComponent : function() {

        var me = this;
		var rec = this.getViewModel().get('jobStepRecord');

		//console.log(rec.data.jobExecutionId);
		var store = Ext.data.StoreManager.lookup('BatchStepExecutionStore');
        //store.getProxy().setExtraParam("jobExecutionId", rec.data.jobExecutionId);
		store.load({
            params: {
                jobExecutionId: rec.data.jobExecutionId
            },
            callback : function(records, operation, success){

				//Ext.getCmp('jobExecutionId').setValue(store.getAt(0).data.jobExecutionId);
				//Ext.getCmp('jobName').setValue(store.getAt(0).data.jobName);
				//Ext.getCmp('startTime').setValue(store.getAt(0).data.startTime);
				//Ext.getCmp('endTime').setValue(store.getAt(0).data.endTime);
				//Ext.getCmp('status').setValue(store.getAt(0).data.status);
				//Ext.getCmp('exitCode').setValue(store.getAt(0).data.exitCode);
				//console.log(store.getAt(0).data);
				//console.log(operation.getProxy().getReader().rawData.params);	//리턴된 json 데이터전체
				
				Ext.getCmp('params').setValue(operation.getProxy().getReader().rawData.message);
            }
        });
		
		this.items =  [{
                xtype: 'fieldset',
                title: 'Job 정보',
				collapsible: true,
                defaults: {
					defaultType: 'textfield'
                },
				layout: 'column',
                store: 'BatchStepExecutionStore',
                items: [
					{
						columnWidth: 0.4,
						xtype: 'fieldcontainer',

						items: [{
							fieldLabel: 'Job 실행 ID',
							name: 'jobExecutionId',
							id: 'jobExecutionId',
							bind : '{jobStepRecord.jobExecutionId}',
							readOnly: true,						
							width: '75%'
						},{
							fieldLabel: 'Job 명칭',
							name: 'jobName',
							id: 'jobName',
							bind : '{jobStepRecord.jobName}',
							readOnly: true,						
							width: '75%'
						},
						{
							fieldLabel: '시작시간',
							name: 'startTime',
							id: 'startTime',
							bind : '{jobStepRecord.startTime}',
							readOnly: true,						
							width: '75%'
						},
                        {
                            fieldLabel: '완료시간',
						    name: 'endTime',
							id: 'endTime',
							bind : '{jobStepRecord.endTime}',
							readOnly: true,						
							width: '75%'
                        }]
					},
					{
						columnWidth: 0.3,
						xtype: 'fieldcontainer',
						items: [{
							fieldLabel: '상태코드',
							name: 'status',
							id: 'status',
							bind : '{jobStepRecord.status}',
							readOnly: true,						
							width: '95%'
						}, {
							fieldLabel: '결과코드',
							name: 'exitCode',
							id: 'exitCode',
							bind : '{jobStepRecord.exitCode}',
							readOnly: true,						
							width: '95%'
						}, {
							xtype: 'textareafield',
							fieldLabel: '파라미터',
							name: 'params',
							id: 'params',
							scroll : true,
							readOnly: true,						
							width: '95%'
						}]
					},{
						columnWidth: 0.3,
						xtype: 'fieldcontainer',
						items: [{
							xtype: 'textareafield',
                            fieldLabel: '결과메세지',
                            name: 'exitMessage',
                            id: 'exitMessage',
                            bind : '{jobStepRecord.exitMessage}',
                            scroll : true,
                            readOnly: true,						
							width: '100%',
							height: '100%'
						}]
					}
	            ],
                listeners: {
                    afterrender: function (form, record) {
                        //Ext.getCmp('jobExecutionId').setValue(store.getAt(0).data.jobExecutionId);
                        //Ext.getCmp('jobName').setValue(store.getAt(0).data.jobName);
                        //Ext.getCmp('startTime').setValue(store.getAt(0).data.startTime);
                        //Ext.getCmp('endTime').setValue(store.getAt(0).data.endTime);
                        //Ext.getCmp('status').setValue(store.getAt(0).data.status);
                        //Ext.getCmp('exitCode').setValue(store.getAt(0).data.exitCode);

                       //console.log(store.getAt(0).data);
                    }
                }
            },
            {
                xtype: 'fieldset',
                title: 'Step 정보',
				collapsible: true,
				defaults: {
                    labelWidth: 100,
                    anchor: '100%',
                    layout: {
                        type: 'hbox',
						defaultMargins: {top: 0, right: 0, bottom: 0, left: 0}
                    }
                },
                items: [
                    {
                        xtype: 'grid',
                        stripeRows:true,
                        columnLines:true,
						columns:[
                            {text:'Step 실행 ID', flex : 1, autoResizeWidth: true, dataIndex:'stepExecutionId', style: 'text-align:center', align:'center', tooltip :'Step 실행 ID'},
                            {text:'Step 명칭', flex : 1, autoResizeWidth: true, dataIndex:'stepName', style: 'text-align:center', align:'center', tooltip :'Step 명칭'},
                            {text:'시작시간', flex : 1, autoResizeWidth: true, dataIndex:'startTime', style: 'text-align:center', align:'center', tooltip : '시작시간'},
                            {text:'완료시간', flex : 1, autoResizeWidth: true, dataIndex:'endTime', style: 'text-align:center', align:'center', tooltip :'완료시간'},
							{text:'C', width : 40, dataIndex:'commitCount', style: 'text-align:center', align:'center', tooltip :'커밋 수'},
                            {text:'R', width : 40, dataIndex:'readCount', style: 'text-align:center', align:'center', tooltip :'읽기 수'},
                            {text:'F', width : 40, dataIndex:'filterCount', style: 'text-align:center', align:'center', tooltip :'필터 수'},
                            {text:'W', width : 40, dataIndex:'writeCount', style: 'text-align:center', align:'center', tooltip :'저장 수'},
                            {text:'RS', width : 40, dataIndex:'readSkipCount', style: 'text-align:center', align:'center', tooltip :'읽기 스킵 수'},
                            {text:'SS', width : 40, dataIndex:'writeSkipCount', style: 'text-align:center', align:'center', tooltip :'저장 스킵 수'},
                            {text:'PS', width : 40, dataIndex:'processSkipCount', style: 'text-align:center', align:'center', tooltip :'프로세스 스킵 수'},
                            {text:'RB', width : 40, dataIndex:'rollbackCount', style: 'text-align:center', align:'center', tooltip :'롤백 수'},
                            {
								text:'상태코드',
								tooltip :'상태코드',
								flex : 1, 
								dataIndex:'status',
								style: 'text-align:center',
								align:'center',
								renderer : function(val) {
									var out = val;
									if (val == "FAILED") {
										return '<b><span style="color:' + "#cf4c35" + ';">' + out + '</span></b>';
									} else if(val == "NOOP") {
										return '<b><span style="color:' + "#73b51e" + ';">' + out + '</span></b>';
									}
									return out;
								}
							},
                            {
								text:'완료코드',
								tooltip :'완료코드',
								flex : 1, 
								dataIndex:'exitCode',
								style: 'text-align:center',
								align:'center',
								renderer : function(val) {
									var out = val;
									if (val == "FAILED") {
										return '<b><span style="color:' + "#cf4c35" + ';">' + out + '</span></b>';
									} else if(val == "NOOP") {
										return '<b><span style="color:' + "#73b51e" + ';">' + out + '</span></b>';
									}
									return out;
								}
							},
                            {text:'결과메세지', flex:1, dataIndex:'exitMessage', style: 'text-align:center', align:'center', tooltip :'결과메세지'}
                        ],
                        listeners: {
                            cellclick : function(view, cell, cellIndex, record, row, rowIndex, e) {
                                var clickedDataIndex = view.panel.headerCt.getHeaderAtIndex(cellIndex).dataIndex;
                                var clickedColumnName = view.panel.headerCt.getHeaderAtIndex(cellIndex).text;
                                var clickedCellValue = record.get(clickedDataIndex);

                                //console.log(clickedCellValue);
                                //Ext.Msg.alert('Cell Value', clickedCellValue);
                                var win = Ext.create('SpringBatch.view.main.StepInfo', {
									viewModel: {
										data: {
										   stepRecord: clickedCellValue,
										   stepColumn: clickedColumnName
										}
									}
								});
								win.show();

                            }
                        },
						viewConfig: {
							columnLines: true,
							emptyText: "데이터가 존재하지 않습니다." ,
							deferEmptyText: false,
							listeners: {
							  refresh: function(dataView) {
								Ext.each(dataView.panel.columns, function(column) {
								  if (column.autoResizeWidth) column.autoSize();
								});
							  }
							}
						},
                        store: 'BatchStepExecutionStore'
                    }
                ]
            }
        ];
        this.callParent(arguments);
    },
	buttons: [{
        text   : 'Job 재구동',
        handler: 'onRestartClick'
    }, {
        text   : 'Job 중지',
        handler: 'onStopClick'
    }, {
        text : '닫기',
        handler : 'onJobStepWinCloseClick'
    }]
});