Ext.define('uio.UioQueryGrid', {
    extend: 'Ext.dataview.List',
    requires: ['uio.UioRecordViewer'],
 
    xtype: 'uioquerygrid',
    fullscreen: true,
    onItemDisclosure: true,
  
    
    detailCard: {
        xtype: 'container',
        layout: 'fit'
    },

    
    select: function(record, arg2, arg3)
    {
    	var recordViewer = Ext.create("uio.UioRecordViewer", {jclass:this.config.jclass, data: record.data, layout:'fit'});
    	Ext.Viewport.add(recordViewer);
    	recordViewer.show();
    	this.callParent(arguments);
    },
    
    constructor: function(config)
    {
    	config.store = {
            fields: config.fields || uio.formdefs[config.jclass].storeFields,
            autoLoad: true,
            proxy: {
            	type: 'ajax',
            	reader: {
            		rootProperty: 'rows',
            	},
            	url: config.url}
        };  
    	config.autoLoad =true;
        config.itemTpl=  config.itemTpl;

    	config.disclosureProperty= config.disclosureProperty;
        config.onItemDisclosure={scope: this,handler: function(record, button, index){this.select(record, button, index)}};

        
        config.plugins=  [
                  {
                      xclass: 'Ext.plugin.ListPaging',
                      autoPaging: true
                  },
                  {xclass: 'Ext.plugin.PullRefresh'}
              ];
        
       this.callParent(arguments);
    }
});
