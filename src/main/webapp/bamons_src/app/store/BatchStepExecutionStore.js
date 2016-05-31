Ext.define('SpringBatch.store.BatchStepExecutionStore', {

    extend: 'Ext.data.Store',
    model: 'SpringBatch.model.BatchStepExecution',
    alias: 'store.BatchStepExecutionStore',
    autoLoad: false,
    proxy: {
        type: 'ajax',
        api: {
        	read: '/step/list.do'
        },
        //select option
        reader: {
            type: 'json',
            successProperty: 'success',
            rootProperty: 'data',
			messageProperty : 'message',
			keepRawData : true
        }
    }
});