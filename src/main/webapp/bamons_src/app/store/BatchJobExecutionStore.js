Ext.define('SpringBatch.store.BatchJobExecutionStore', {

    extend: 'Ext.data.Store',
    model: 'SpringBatch.model.BatchJobExecution',
    alias: 'store.BatchJobExecutionStore',
    autoLoad: true,
    pageSize: 25,
    proxy: {
        type: 'ajax',
        api: {
        	// insert page
        	//create : '/create',
        	// select page
        	read: '/job/list.do'
        	// update page
        	//update : '/update',
        	// delete page
        	//destroy : '/delete'
        },
        //select option
        reader: {
            type: 'json',
            successProperty: 'success',
            rootProperty: 'data',
            totalProperty: 'totalCount'
        }
       //create,update,delete option
       //writer: {
	    //    type: 'json',
	    //    //그리드 row의 모든값 전송
	    //    writeAllFields: true,
	    //    //필수속성값
	    //    encode : true,
	    //    //server (request param id)
	    //    rootProperty: 'data'
	    //}
    }
});
