Ext.define('SpringBatch.view.main.BatchList', {

    extend: 'Ext.grid.Panel',
    xtype: 'batchlist',

    requires: [
        'SpringBatch.store.BatchJobExecutionStore'
    ],

    title: '배치 결과',
    store: 'BatchJobExecutionStore',
	loadMask: true,
	columnLines: true,
    width: 900,

    tbar: [{
       text: '특정 Job 구동팝업',
       tooltip: '특정 Job 구동하기',
       handler: 'onStartWinClick'
    },'->',{
		xtype: 'datefield',
		reference: 'startTime',
		name: 'startTime',
		submitFormat: 'Y-m-d',
		format: 'Y-m-d',
		allowBlank: false
    },{
       xtype: 'button',
	   text: 'Job 구동 시작시간 검색',
	   handler: 'onSearchClick'
    },{
       xtype: 'button',
	   text: '초기화',
	   handler: 'onSearchResetClick'
    }],
    /*
    * 헤더와 컬럼의 정렬이 동일할 경우 align 만 사용
    * 헤더 정렬  ->  style: 'text-align:center'
    * 컬럼 정렬  ->   align:' { left || center || right }'
    */
    columns: [
        { text: 'Job 실행 ID', dataIndex: 'jobExecutionId', flex: 1, style: 'text-align:center', align:'center', autoResizeWidth: true},
        { text: 'Job 명칭', dataIndex: 'jobName', flex: 1, style: 'text-align:center', align:'center'},
		{ text: 'Job Instance ID', dataIndex: 'jobInstanceId', flex: 1, style: 'text-align:center', align:'center', autoResizeWidth: true},
        { text: '시작시간', dataIndex: 'startTime', flex: 1, style: 'text-align:center', align:'center', autoResizeWidth: true},
        { text: '완료시간', dataIndex: 'endTime', flex: 1, style: 'text-align:center', align:'center', autoResizeWidth: true},
        {
			text: '상태코드',
			autoResizeWidth: true,
			dataIndex: 'status',
			flex: 1,
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
			text: '결과코드',
			autoResizeWidth: true,
			dataIndex: 'exitCode',
			flex: 1,
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
		}
    ],
	viewConfig: {
		listeners: {
		  refresh: function(dataView) {
			Ext.each(dataView.panel.columns, function(column) {
			  if (column.autoResizeWidth) column.autoSize();
			});
		  }
		}
	},
    dockedItems : [
		{
			dock: 'bottom',
			xtype: 'pagingtoolbar',
			store: 'BatchJobExecutionStore',
			displayInfo: true,
			displayMsg: '현재데이터수 : {0} - {1} / 총 데이터수 : {2}',
			emptyMsg: "데이터가 존재하지 않습니다."
    }],
    listeners: {
        //select: 'onItemSelected'
        itemdblclick: 'onItemDblClick'
    }

});