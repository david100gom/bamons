Ext.define('SpringBatch.store.BatchJobExecutionStore', {

    extend: 'Ext.data.Store',
    model: 'SpringBatch.model.BatchJobExecution',
    alias: 'store.BatchJobExecutionStore',
    autoLoad: true,
    pageSize: 25,
    proxy: {
        type: 'ajax',
        api: {
        	read: '/job/list.do'
        },
        reader: {
            type: 'json',
            successProperty: 'success',
            rootProperty: 'data',
            totalProperty: 'totalCount'
        }
    }
});
